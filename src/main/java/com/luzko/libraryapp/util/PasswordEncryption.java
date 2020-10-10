package com.luzko.libraryapp.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class PasswordEncryption {
    private static final Logger logger = LogManager.getLogger(PasswordEncryption.class);
    private static final String ENCRYPTION_ALGORITHM = "SHA-1";

    private PasswordEncryption() {

    }

    public static String encrypt(String password) {
        String encryptedPassword = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ENCRYPTION_ALGORITHM);
            messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] passwordEncodedBytes = messageDigest.digest();
            BigInteger passwordBigInt = new BigInteger(1, passwordEncodedBytes);
            encryptedPassword = passwordBigInt.toString(16);
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.ERROR, "Error while encrypt password", e);
        }
        return encryptedPassword;
    }
}

