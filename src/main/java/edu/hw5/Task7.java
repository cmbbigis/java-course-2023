package edu.hw5;

import java.util.regex.Pattern;

public final class Task7 {
    private Task7() {
    }

    public static boolean isMatchesPattern1(String string) {
        var pattern = Pattern.compile("^[01]{2}0.*$");
        return pattern.matcher(string).matches();
    }

    public static boolean isMatchesPattern2(String string) {
        var pattern = Pattern.compile("^([01])[01]*\\1$");
        return pattern.matcher(string).matches();
    }

    public static boolean isLengthAtLeast1AndNoMoreThan3(String string) {
        var pattern = Pattern.compile("^[01]{1,3}$");
        return pattern.matcher(string).matches();
    }
}
