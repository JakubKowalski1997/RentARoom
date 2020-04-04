package com.edu.agh.fis.RentARoom.security.user.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class PasswordGenerator {

    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private PasswordUtils passwordUtils;
    private boolean useLower;
    private boolean useUpper;
    private boolean useDigits;


    private PasswordGenerator() {
        throw new UnsupportedOperationException("Empty constructor is not supported.");
    }

    private PasswordGenerator(PasswordGeneratorBuilder builder) {
        passwordUtils = new PasswordUtils();
        this.useLower = builder.useLower;
        this.useUpper = builder.useUpper;
        this.useDigits = builder.useDigits;
    }

    public String generate(int length) {
        // Argument Validation.
        if (length <= 0) {
            return "";
        }

        // Variables.
        StringBuilder password = new StringBuilder(length);
        Random random = new Random(System.nanoTime());

        // Collect the categories to use.
        List<String> charCategories = new ArrayList<>(3);
        if (useLower) {
            charCategories.add(LOWER);
        }
        if (useUpper) {
            charCategories.add(UPPER);
        }
        if (useDigits) {
            charCategories.add(DIGITS);
        }

        do {
            password.setLength(0);
            // Build the password.
            for (int i = 0; i < length; i++) {
                String charCategory = charCategories.get(random.nextInt(charCategories.size()));
                int position = random.nextInt(charCategory.length());
                password.append(charCategory.charAt(position));
            }

        } while (!passwordUtils.checkPassword((password.toString()), this.useLower, this.useUpper, this.useDigits));

        return new String(password);
    }


    public static class PasswordGeneratorBuilder {

        private boolean useLower;
        private boolean useUpper;
        private boolean useDigits;
        private boolean usePunctuation;

        public PasswordGeneratorBuilder() {
            this.useLower = false;
            this.useUpper = false;
            this.useDigits = false;
            this.usePunctuation = false;
        }

        public PasswordGeneratorBuilder useLower(boolean useLower) {
            this.useLower = useLower;
            return this;
        }

        public PasswordGeneratorBuilder useUpper(boolean useUpper) {
            this.useUpper = useUpper;
            return this;
        }

        public PasswordGeneratorBuilder useDigits(boolean useDigits) {
            this.useDigits = useDigits;
            return this;
        }

        public PasswordGenerator build() {
            return new PasswordGenerator(this);
        }
    }
}