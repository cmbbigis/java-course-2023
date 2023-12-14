package edu.hw5;

import java.util.regex.Pattern;

public final class Task8 {
    private Task8() {
    }

    public static boolean isMatchesPattern1(String string) {
        var pattern = Pattern.compile("^([01]{2})*[01]$");
        return pattern.matcher(string).matches();
    }

    public static boolean isMatchesPattern2(String string) {
        var pattern = Pattern.compile("^(0([01]{2})*1?|1([01]{2})*0?)$");
        return pattern.matcher(string).matches();
    }

    public static boolean isMatchesPattern3(String string) {
        var pattern = Pattern.compile("^1*(01*01*01*)*$");
        return pattern.matcher(string).matches();
    }

    public static boolean isMatchesPattern4(String string) {
        var pattern = Pattern.compile("^(?!11$|111$)[01]*$");
        return pattern.matcher(string).matches();
    }

    public static boolean isMatchesPattern5(String string) {
        var pattern = Pattern.compile("^(1[01])*1?$");
        return pattern.matcher(string).matches();
    }

    public static boolean isMatchesPattern6(String string) {
        var pattern = Pattern.compile("^(0*10*0*)?$");
        return pattern.matcher(string).matches();
    }
}
