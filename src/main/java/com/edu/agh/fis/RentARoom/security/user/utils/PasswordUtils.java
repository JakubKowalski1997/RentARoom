package com.edu.agh.fis.RentARoom.security.user.utils;

import com.edu.agh.fis.RentARoom.security.DTOs.ChangePasswordRequest;
import com.edu.agh.fis.RentARoom.security.user.model.User;
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

    public String checkNewPassword(ChangePasswordRequest password, User user) {

        if (!bCryptPasswordEncoder.matches(password.oldPassword, user.getPassword())) {
            return "wrong old password";
        }
        if (!password.newPassword.equals(password.newPasswordConfirm)) {
            return "passwords are not same";
        }
        if (bCryptPasswordEncoder.matches(password.newPassword, user.getPassword())) {
            return "New password is same as your current password";
        }
        if (!checkPassword(password.newPassword, false, false, false)) {
            return "wrong format of password";
        }
        return "Valid";
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

