package com.luzko.libraryapp.util.mail;

import com.luzko.libraryapp.util.ConfigurationManager;

import java.util.Properties;

public class EmailSenderUtil {
    //private static final Logger LOGGER = LogManager.getLogger();
    private static final String EMAIL_HEAD = "Libraryapp confirmation";
    private static final String EMAIL_BODY = "Confirmation code: ";
    //private static final String FILE_NAME = "property/mail.properties";

    private EmailSenderUtil() {

    }

    public static void sendMessage(String email, String codeConfirm) {
        //try {
        Properties properties = ConfigurationManager.getMailProperties();
        //InputStream inputStream = EmailSenderUtil.class.getClassLoader().getResourceAsStream(FILE_NAME);
        //properties.load(inputStream);
        Thread thread = new Thread(new EmailSenderThread(
                email, EMAIL_HEAD, EMAIL_BODY + codeConfirm, properties));
        thread.start();
        //} catch (IOException e) {
        //LOGGER.log(Level.ERROR, "Error with mail properties file", e);
        //}
        //}
    }
}
