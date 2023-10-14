package edu.hw1;

import java.util.Arrays;

public final class Task6 {
    private Task6() {
    }

    public static int countK(int number) {
        return countK(number, 0);
    }

    private static int countK(int number, int count) {
        if (count + 1 == 8) {
            return -1;
        }
        var digitsChars = Integer.toString(number).toCharArray();
        Arrays.sort(digitsChars);
        var sortedNumberString = new String(digitsChars);
        var ascendingNumber = Integer.parseInt(sortedNumberString);
        var descendingNumber = ascendingNumber > 0 ? Integer.parseInt(reverseString(sortedNumberString)) :
            Integer.parseInt(reverseString(Integer.toString(Integer.parseInt(sortedNumberString) * -1))) * -1;
        if (descendingNumber - ascendingNumber == 6174) {
            return count + 1;
        }
        return countK(descendingNumber - ascendingNumber, count + 1);
    }

    private static String reverseString(String string) {
        return new StringBuilder(string).reverse().toString();
    }

}
