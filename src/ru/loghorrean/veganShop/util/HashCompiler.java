package ru.loghorrean.veganShop.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class HashCompiler {
    private static SecureRandom r = new SecureRandom();
    private static int start = 33;
    private static int end = 122;
    public static String hashPassword(String password, String salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String newPassword = password + salt;
            byte[] encodedHash = digest.digest(newPassword.getBytes(StandardCharsets.UTF_8));
            StringBuilder builder = new StringBuilder(64);
            for(byte b : encodedHash) {
                builder.append(String.format("%02x", b));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getRandomSalt() {
        StringBuilder builder = new StringBuilder(10);
        for (int i = 0; i < MainConstants.getSaltLength(); ++i) {
            int randomNumber = start + (int)(Math.random() * ((end-start) + 1));
            char c = (char)(randomNumber);
            builder.append(c);
        }
        return builder.toString();
    }
}
