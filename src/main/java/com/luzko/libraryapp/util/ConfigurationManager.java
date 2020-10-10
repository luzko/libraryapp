package com.luzko.libraryapp.util;

import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

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

    public static String getDatabaseProperty(String key) {
        return databaseProperties.getProperty(key);
    }

    public static String getMailProperty(String key) {
        return mailProperties.getProperty(key);
    }

    public static String getMessageProperty(String key) {
        return messageProperties.getProperty(key);
    }

    public static Properties getDatabaseProperties() {
        return databaseProperties;
    }

    public static Properties getMessageProperties() {
        return messageProperties;
    }

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