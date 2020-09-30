package com.luzko.libraryapp.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryption {
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
            //LOGGER.log(Level.ERROR, "Error while encrypt password", e);
        }
        return encryptedPassword;
    }
}

