package edu.hw1;

public final class Task4 {
    private Task4() {

    }

    public static String fixString(String mixedString) {
        var result = mixedString.toCharArray();
        for (var i = 1; i < result.length; i += 2) {
            var tmp = result[i];
            result[i] = result[i - 1];
            result[i - 1] = tmp;
        }
        return new String(result);
    }
}
