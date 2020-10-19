package com.luzko.libraryapp.util;

import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * The type Configuration manager.
 */
public final class ConfigurationManager {
    private static final String DATABASE_RESOURCE = "prop.database";
    private static final String MAIL_RESOURCE = "prop.mail";
    private static final String MESSAGE_RESOURCE = "prop.message";
    private static Properties databaseProperties;
    private static Properties mailProperties;
    private static Properties messageProperties;

    static {
        loadProperties();
    }

    private ConfigurationManager() {

    }

    /**
     * Gets database property.
     *
     * @param key the key
     * @return the database property
     */
    public static String getDatabaseProperty(String key) {
        return databaseProperties.getProperty(key);
    }

    /**
     * Gets mail property.
     *
     * @param key the key
     * @return the mail property
     */
    public static String getMailProperty(String key) {
        return mailProperties.getProperty(key);
    }

    /**
     * Gets message property.
     *
     * @param key the key
     * @return the message property
     */
    public static String getMessageProperty(String key) {
        return messageProperties.getProperty(key);
    }

    /**
     * Gets database properties.
     *
     * @return the database properties
     */
    public static Properties getDatabaseProperties() {
        return databaseProperties;
    }

    /**
     * Gets message properties.
     *
     * @return the message properties
     */
    public static Properties getMessageProperties() {
        return messageProperties;
    }

    /**
     * Gets mail properties.
     *
     * @return the mail properties
     */
    public static Properties getMailProperties() {
        return mailProperties;
    }

    private static void loadProperties() {
        ResourceBundle resourceBundleDatabase = ResourceBundle.getBundle(DATABASE_RESOURCE);
        ResourceBundle resourceBundleMail = ResourceBundle.getBundle(MAIL_RESOURCE);
        ResourceBundle resourceBundleMessage = ResourceBundle.getBundle(MESSAGE_RESOURCE);

        databaseProperties = convertResourceBundleToProperties(resourceBundleDatabase);
        mailProperties = convertResourceBundleToProperties(resourceBundleMail);
        messageProperties = convertResourceBundleToProperties(resourceBundleMessage);
    }

    private static Properties convertResourceBundleToProperties(ResourceBundle resourceBundle) {
        Properties properties = new Properties();
        Enumeration<String> keys = resourceBundle.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            properties.put(key, resourceBundle.getString(key));
        }
        return properties;
    }
}