package com.edu.agh.fis.RentARoom.security.user.utils;

import com.edu.agh.fis.RentARoom.security.user.DTOs.ChangePasswordRequest;
import com.edu.agh.fis.RentARoom.security.user.DTOs.ChangePasswordResponse;
import com.edu.agh.fis.RentARoom.security.user.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Random;

/**
 * Password utils
 *
 *
 * @author  Jakub Kowalski
 * @version 1.0
 * @since   2020-05-30
 */
public final class PasswordUtils {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public PasswordUtils() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * This method is used to check correctness of password
     * @param password password to check
     * @param useLower password contains lower
     * @param useUpper password contains upper
     * @param useDigits password contains digits
     */
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

    /**
     * This method is used to generate password
     */
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

    public ChangePasswordResponse checkNewPassword(ChangePasswordRequest password, User user) {

        if (!password.getNewPassword().equals(password.getNewPasswordConfirm())) {
            return new ChangePasswordResponse(false,"Password and confirm password are not the same");
        }
        if (bCryptPasswordEncoder.matches(password.getNewPassword(), user.getPassword())) {
            return new ChangePasswordResponse(false,"New password is same as your current password");
        }
        if (!checkPassword(password.getNewPassword(), false, false, false)) {
            return new ChangePasswordResponse(false,"Wrong format of password");
        }
        return new ChangePasswordResponse(true,"Your password has been changed");
    }
}

