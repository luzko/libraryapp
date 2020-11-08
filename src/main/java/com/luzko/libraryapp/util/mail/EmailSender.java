package com.luzko.libraryapp.util.mail;

import com.luzko.libraryapp.util.ConfigurationManager;

import java.util.Properties;

/**
 * The type represents the email sender.
 */
public class EmailSender {
    private static final Properties properties = ConfigurationManager.getMailProperties();
    private static final String MAIL_USER_NAME = "mail.user.name";
    private static final String EMAIL_SUBJECT = "Libraryapp confirmation";
    private static final String EMAIL_BODY = "Confirmation code: ";

    private EmailSender() {

    }

    /**
     * Send message with confirm code.
     *
     * @param emailTo     the email address
     * @param codeConfirm the code confirm
     */
    public static void sendMessageConfirm(String emailTo, String codeConfirm) {
        sendMessage(emailTo, EMAIL_SUBJECT, EMAIL_BODY + codeConfirm);
    }

    /**
     * Send message to admin.
     *
     * @param login   the user login
     * @param subject the message subject
     * @param text    the message text
     */
    public static void sendMessageAdmin(String login, String subject, String text) {
        sendMessage(properties.getProperty(MAIL_USER_NAME), login + " : " + subject, text);
    }

    private static void sendMessage(String toEmail, String mailSubject, String mailText) {
        EmailSenderThread emailSender = new EmailSenderThread(toEmail, mailSubject, mailText, properties);
        Thread thread = new Thread(emailSender);
        thread.start();
    }
}
