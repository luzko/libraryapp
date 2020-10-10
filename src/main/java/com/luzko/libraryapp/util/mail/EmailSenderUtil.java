package com.luzko.libraryapp.util.mail;

import com.luzko.libraryapp.util.ConfigurationManager;

import java.util.Properties;

public class EmailSenderUtil {
    private static final Properties properties = ConfigurationManager.getMailProperties();
    private static final String MAIL_USER_NAME = "mail.user.name";
    private static final String EMAIL_SUBJECT = "Libraryapp confirmation";
    private static final String EMAIL_BODY = "Confirmation code: ";

    private EmailSenderUtil() {

    }

    public static void sendMessageConfirm(String emailTo, String codeConfirm) {
        sendMessage(emailTo, EMAIL_SUBJECT, EMAIL_BODY + codeConfirm);
    }

    public static void setMessageAdmin(String login, String subject, String text) {
        sendMessage(properties.getProperty(MAIL_USER_NAME), login + " : " + subject, text);
    }

    public static void sendMessage(String toEmail, String mailSubject, String mailText) {
        EmailSenderThread emailSender = new EmailSenderThread(toEmail, mailSubject, mailText, properties);
        Thread thread = new Thread(emailSender);
        thread.start();
    }
}
