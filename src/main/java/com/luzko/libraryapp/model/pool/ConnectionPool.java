package com.luzko.libraryapp.model.pool;

import com.luzko.libraryapp.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The type represents the connection pool.
 */
public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static final String DRIVER_NAME = "driver";
    private static final String URL = "url";
    private static final int POOL_SIZE = 8;
    private final Properties properties;
    private final BlockingQueue<ProxyConnection> availableConnection;
    private final Queue<ProxyConnection> givenConnection;
    private final AtomicBoolean isInitialized;
    private final AtomicBoolean isPoolClosing;
    private final Lock initLock;

    private ConnectionPool() {
        properties = ConfigurationManager.getDatabaseProperties();
        availableConnection = new LinkedBlockingQueue<>(POOL_SIZE);
        givenConnection = new ArrayDeque<>();
        isInitialized = new AtomicBoolean(false);
        isPoolClosing = new AtomicBoolean(false);
        initLock = new ReentrantLock();
    }

    private static class ConnectionPoolSingletonHolder {
        /**
         * The connection pool instance.
         */
        static final ConnectionPool INSTANCE = new ConnectionPool();
    }

    /**
     * Gets connection pool instance.
     *
     * @return the connection pool instance
     */
    public static ConnectionPool getInstance() {
        return ConnectionPoolSingletonHolder.INSTANCE;
    }

    /**
     * Init connection pool.
     */
    public void init() {
        initLock.lock();
        if (!isInitialized.get()) {
            try {
                Class.forName(properties.getProperty(DRIVER_NAME));
                createConnections();
                isInitialized.set(true);
            } catch (ClassNotFoundException e) {
                logger.log(Level.FATAL, "Error in create connection pool", e);
                throw new RuntimeException("Error in create connection pool", e);
            } finally {
                initLock.unlock();
            }
        }
    }

    private void createConnections() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                Connection connection = DriverManager.getConnection(properties.getProperty(URL), properties);
                availableConnection.offer(new ProxyConnection(connection));
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Connection not created", e);
            }
        }
    }

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        ProxyConnection connection = null;
        if (!isPoolClosing.get()) {
            try {
                connection = availableConnection.take();
                givenConnection.offer(connection);
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "Connection pool can't provide connection.", e);
            }
        }
        return connection;
    }

    /**
     * Destroy connection pool.
     */
    public void destroy() {
        isPoolClosing.set(true);
        initLock.lock();
        for (int i = 0; i < availableConnection.size(); i++) {
            try {
                closeConnection(availableConnection.take());
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "Error destroy connection.", e);
            }
        }
        try {
            deregisterDrivers();
            isInitialized.set(false);
            isPoolClosing.set(false);
        } finally {
            initLock.unlock();
        }
    }

    private void closeConnection(ProxyConnection connection) {
        try {
            connection.reallyClose();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't close connection", e);
        }
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Error in deregister drivers", e);
            }
        });
    }

    /**
     * Release connection.
     *
     * @param connection the connection
     */
    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection && givenConnection.remove(connection)) {
            availableConnection.offer((ProxyConnection) connection);
        } else {
            logger.log(Level.WARN, "Invalid connection");
        }
    }
}
