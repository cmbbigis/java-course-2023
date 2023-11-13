package edu.hw5;

import java.util.regex.Pattern;

public final class Task4 {
    private Task4() {
    }

    public static boolean isPasswordValid(String password) {
        Pattern pattern = Pattern.compile("[~!@#$%^&*|]");
        return pattern.matcher(password).find();
    }
}
