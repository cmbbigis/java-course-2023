package edu.hw3;

public final class Task1 {
    private Task1() {
    }

    final private static int FIRST_LOWER_CHAR_INDEX = 97;
    final private static int LAST_LOWER_CHAR_INDEX = 122;
    final private static int FIRST_UPPER_CHAR_INDEX = 65;
    final private static int LAST_UPPER_CHAR_INDEX = 90;


    public static String atbash(String string) {
        var resultSb = new StringBuilder();
        for (var i = 0; i < string.length(); i++) {
            var currentChar = string.charAt(i);
            if (Character.isAlphabetic(currentChar)) {
                if (Character.isLowerCase(currentChar)) {
                    resultSb.append(Character.toChars(
                        LAST_LOWER_CHAR_INDEX - (currentChar - FIRST_LOWER_CHAR_INDEX))[0]);
                } else {
                    resultSb.append(Character.toChars(
                        LAST_UPPER_CHAR_INDEX - (currentChar - FIRST_UPPER_CHAR_INDEX))[0]);
                }
            } else {
                resultSb.append(currentChar);
            }
        }
        return resultSb.toString();
    }
}
