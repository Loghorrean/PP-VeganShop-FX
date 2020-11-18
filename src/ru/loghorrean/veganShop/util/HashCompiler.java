package ru.loghorrean.veganShop.util;

import java.util.Random;

public class HashCompiler {
    private static Random r = new Random();
    public static String hashPassword(String password, String salt) {
        
    }

    public static String getRandomSalt() {
        StringBuilder builder = new StringBuilder(10);
        for (int i = 0; i < MainConstants.getSaltLength(); ++i) {
            char c = (char)(r.nextInt((int)(Character.MAX_VALUE)));
            builder.append(c);
        }
        return builder.toString();
    }
}
