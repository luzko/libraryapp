package com.luzko.libraryapp.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionPool {
    public static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static final String PROPERTIES_FILENAME = "db.database";
    private static final String DRIVER_NAME = "driver";
    private static final String URL = "url";
    private static final int POOL_SIZE = 16;
    private final BlockingQueue<ProxyConnection> freeConnections;
    private final Queue<ProxyConnection> givenConnections;
    private static final ConnectionPool connectionPool = new ConnectionPool();

    public static ConnectionPool getInstance() {
        return connectionPool;
    }

    private ConnectionPool() {
        freeConnections = new LinkedBlockingDeque<>(POOL_SIZE);
        givenConnections = new ArrayDeque<>();

        ResourceBundle resourceBundle = ResourceBundle.getBundle(PROPERTIES_FILENAME);
        Properties properties = convertResourceBundleToProperties(resourceBundle);

        try {
            Class.forName(resourceBundle.getString(DRIVER_NAME));
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(resourceBundle.getString(URL), properties);
                freeConnections.offer(new ProxyConnection(connection));
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.log(Level.FATAL, "Error in create connection pool.", e);
            throw new RuntimeException("Error in create connection pool.", e);
        }
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Connection pool can't provide connection.", e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            if (givenConnections.remove(connection)) {
                freeConnections.offer((ProxyConnection) connection);
            }
        } else {
            logger.log(Level.WARN, "Invalid connection.");
        }
    }

    public void destroyPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (SQLException | InterruptedException e) {
                logger.log(Level.ERROR, "Error in destroy pool.", e);
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Error in deregister drivers.", e);
            }
        });
    }

    private Properties convertResourceBundleToProperties(ResourceBundle resourceBundle) {
        Properties properties = new Properties();
        Enumeration<String> keys = resourceBundle.getKeys();

        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            properties.put(key, resourceBundle.getString(key));
        }
        return properties;
    }
}