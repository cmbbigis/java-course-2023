package edu.hw5;

import java.util.regex.Pattern;

public final class Task4 {
    private Task4() {
    }

    private final static Pattern PATTERN = Pattern.compile("[~!@#$%^&*|]");

    public static boolean isPasswordValid(String password) {
        return PATTERN.matcher(password).find();
    }
}
