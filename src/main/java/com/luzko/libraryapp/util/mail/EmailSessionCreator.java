package com.luzko.libraryapp.util.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 * The type represents the email session creator.
 */
public class EmailSessionCreator {
    private static final String MAIL_USER_NAME = "mail.user.name";
    private static final String MAIL_USER_PASSWORD = "mail.user.password";

    private EmailSessionCreator() {

    }

    /**
     * Create email session.
     *
     * @param properties the properties
     * @return the session
     */
    public static Session createSession(Properties properties) {
        String userName = properties.getProperty(MAIL_USER_NAME);
        String userPassword = properties.getProperty(MAIL_USER_PASSWORD);
        return Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, userPassword);
            }
        });
    }
}


