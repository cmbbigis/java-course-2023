package edu.hw1;

public final class Task2 {
    final private static int TEN = 10;

    public static int countDigits(int number) {
        var privateNumber = number;
        if (privateNumber < TEN) {
            return 1;
        }
        var count = 0;
        while (privateNumber != 0) {
            count++;
            privateNumber /= TEN;
        }
        return count;
    }
}
