package com.luzko.libraryapp.util.mail;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSenderThread implements Runnable {
    private static final Logger logger = LogManager.getLogger(EmailSenderThread.class);
    private static final String MAIL_USER_NAME = "mail.user.name";

    private MimeMessage message;
    private final String toEmail;
    private final String mailSubject;
    private final String mailText;
    private final Properties properties;

    public EmailSenderThread(String toEmail, String mailSubject, String mailText, Properties properties) {
        this.toEmail = toEmail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
        this.properties = properties;
    }

    private void initMessage() {
        Session mailSession = com.luzko.libraryapp.util.mail.EmailSessionCreator.createSession(properties);
        try {
            message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(properties.getProperty(MAIL_USER_NAME)));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(mailSubject);
            message.setText(mailText);
        } catch (AddressException e) {
            logger.log(Level.ERROR, "Invalid address: {}", toEmail, e);
        } catch (MessagingException e) {
            logger.log(Level.ERROR, "Error sending message", e);
        }
    }

    @Override
    public void run() {
        initMessage();
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            logger.log(Level.ERROR, "Error sending message", e);
        }
    }
}
