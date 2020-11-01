package com.luzko.libraryapp.util;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * The type Configuration manager.
 */
public final class ConfigurationManager {
    private static final String LOCALE_RU = "ru_RU";
    private static final String DATABASE_RESOURCE = "prop.database";
    private static final String MAIL_RESOURCE = "prop.mail";
    private static final String MESSAGE_RESOURCE = "prop.message";
    private static Properties databaseProperties;
    private static Properties mailProperties;
    private static Properties messagePropertiesEN;
    private static Properties messagePropertiesRU;

    static {
        loadProperties();
    }

    private ConfigurationManager() {

    }

    /**
     * Gets message property.
     *
     * @param key the key
     * @return the message property
     */
    public static String getMessageProperty(String key, String locale) {
        System.out.println(locale);
        return locale.equals(LOCALE_RU) ? messagePropertiesRU.getProperty(key) : messagePropertiesEN.getProperty(key);
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
     * Gets mail properties.
     *
     * @return the mail properties
     */
    public static Properties getMailProperties() {
        return mailProperties;
    }

    private static void loadProperties() {
        Locale.setDefault(Locale.US);
        ResourceBundle resourceBundleDatabase = ResourceBundle.getBundle(DATABASE_RESOURCE);
        ResourceBundle resourceBundleMail = ResourceBundle.getBundle(MAIL_RESOURCE);
        ResourceBundle resourceBundleMessageEN = ResourceBundle.getBundle(MESSAGE_RESOURCE);
        ResourceBundle resourceBundleMessageRU = ResourceBundle.getBundle(MESSAGE_RESOURCE, new Locale("ru", "RU"));
        //TODO

        databaseProperties = convertResourceBundleToProperties(resourceBundleDatabase);
        mailProperties = convertResourceBundleToProperties(resourceBundleMail);
        messagePropertiesEN = convertResourceBundleToProperties(resourceBundleMessageEN);
        messagePropertiesRU = convertResourceBundleToProperties(resourceBundleMessageRU);
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