package com.luzko.libraryapp.config;

import com.luzko.libraryapp.exception.PropertiesException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigurationManager {
    public static final Logger logger = LogManager.getLogger(ConfigurationManager.class);

    private static final String MAIL_RESOURCE = "mail.properties";
    private static final String MESS_RESOURCE = "message.properties";
    private static Properties mailProperties;
    private static Properties messageProperties;

    static {
        try {
            loadProperties();
        } catch (PropertiesException e) {
            logger.log(Level.ERROR, "Properties error", e);
        }
    }

    private ConfigurationManager() {

    }

    public static String getMailProperty(String key) {
        return mailProperties.getProperty(key);
    }

    public static String getMessageProperty(String key) {
        return messageProperties.getProperty(key);
    }

    private static void loadProperties() throws PropertiesException {
        try {
            mailProperties = new Properties();
            InputStream mailStream = ConfigurationManager.class.getClassLoader().getResourceAsStream(MAIL_RESOURCE);
            mailProperties.load(mailStream);

            messageProperties = new Properties();
            InputStream messageLoad = ConfigurationManager.class.getClassLoader().getResourceAsStream(MESS_RESOURCE);
            messageProperties.load(messageLoad);
        } catch (IOException e) {
            throw new PropertiesException(e);
        }
    }
}
