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
    private MimeMessage message;
    private final String sendToEmail;
    private final String mailSubject;
    private final String mailText;
    private final Properties properties;
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String TEXT_TYPE = "text/html";

    public EmailSenderThread(String sendToEmail, String mailSubject, String mailText, Properties properties) {
        this.sendToEmail = sendToEmail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
        this.properties = properties;
    }

    private void initMessage() {
        //Session mailSession = com.borikov.bullfinch.util.EmailSessionCreator.createSession(properties);
        //Session mailSession = EmailSessionCreator.createSession(properties);
        Session mailSession = com.luzko.libraryapp.util.mail.EmailSessionCreator.createSession(properties);
        try {
            message = new MimeMessage(mailSession);
            //от кого
            message.setFrom(new InternetAddress("libraryapp.app@gmail.com"));
            //кому
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendToEmail));
            //тема сообщения
            message.setSubject(mailSubject);
            //текст
            message.setText(mailText);
            //message.setSubject(mailSubject);
            //message.setContent(mailText, TEXT_TYPE);
            //message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
        } catch (AddressException e) {
            LOGGER.log(Level.ERROR, "Invalid address: {}", sendToEmail, e);
        } catch (MessagingException e) {
            LOGGER.log(Level.ERROR, "Error generating or sending message: ", e);
        }
    }

    @Override
    public void run() {
        initMessage();
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            LOGGER.log(Level.ERROR, "Error generating or sending message: ", e);
        }
    }
}
