package com.edu.agh.fis.RentARoom.security.user.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Random;

public final class PasswordUtils {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public PasswordUtils() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public boolean checkPassword(String password, boolean useLower, boolean useUpper, boolean useDigits) {
        if (password.length() < 8)
            return false;

        if (useLower && !password.matches(".*[0-9]+.*")) {
            return false;
        }
        if (useUpper && !password.matches(".*[A-Z]+.*")) {
            return false;
        }
        return !useDigits || password.matches(".*[a-z]+.*");
    }

    public static String generateTemporaryPassword() {
        PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .build();
        Random rnd = new Random();
        int len = 7 + rnd.nextInt(15);
        return passwordGenerator.generate(len);
    }
}

