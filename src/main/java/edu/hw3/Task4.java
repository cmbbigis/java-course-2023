package edu.hw3;

public final class Task4 {
    private Task4() {
    }

    private static final String[]
        ROMANS = new String[] {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private static final int[] ARABIC = new int[] {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    public static String convertToRoman(int number) {
        var result = new StringBuilder();
        var localNumber = number;
        for (var i = 0; i < ROMANS.length; i++) {
            while (localNumber >= ARABIC[i]) {
                var d = localNumber / ARABIC[i];
                localNumber = localNumber % ARABIC[i];
                result.append(ROMANS[i].repeat(d));
            }
        }
        return result.toString();
    }
}
