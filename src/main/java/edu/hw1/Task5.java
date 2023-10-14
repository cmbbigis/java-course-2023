package edu.hw1;

public final class Task5 {
    private Task5() {
    }

    public static boolean isPalindromeDescendant(int number) {
        var string = Integer.toString(number);
        if (string.length() < 2) {
            return false;
        }
        if (string.contentEquals(new StringBuilder(string).reverse())) {
            return true;
        }
        var descendantSb = new StringBuilder();
        for (var i = 1; i < string.length(); i += 2) {
            descendantSb.append(Integer.parseInt("" + string.charAt(i)) +
                Integer.parseInt("" + string.charAt(i - 1)));
        }
        var descendant = descendantSb.toString();
        if (descendant.contentEquals(descendantSb.reverse())) {
            return true;
        }
        return isPalindromeDescendant(Integer.parseInt(descendant));
    }
}
