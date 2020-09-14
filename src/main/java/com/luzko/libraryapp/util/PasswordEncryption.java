package com.luzko.libraryapp.util;

import com.luzko.libraryapp.model.dao.impl.UserDaoImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class PasswordEncryption {
    private static final String ENCRYPTION_ALGORITHM = "SHA-1";
    private static final PasswordEncryption INSTANCE = new PasswordEncryption();
    //private static final Logger LOGGER = LogManager.getLogger();

    public static PasswordEncryption getInstance() {
        return INSTANCE;
    }

    private PasswordEncryption() {
    }

    public static void main(String[] args) {
        System.out.println(new PasswordEncryption().encrypt("qwer"));
    }

    public String encrypt(String password) {
        String encryptedPassword = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ENCRYPTION_ALGORITHM);
            messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] passwordEncodedBytes = messageDigest.digest();
            BigInteger passwordBigInt = new BigInteger(1, passwordEncodedBytes);
            encryptedPassword = passwordBigInt.toString(16);
        } catch (NoSuchAlgorithmException e) {
            //LOGGER.log(Level.ERROR, "Error while encrypt password");
        }
        return encryptedPassword;
    }
}

