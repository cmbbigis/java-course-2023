package edu.hw1;

public final class Task2 {
    private Task2() {
    }

    public static int countDigits(int numbers) {
        if (numbers < 10) {
            return 1;
        }
        var count = 0;
        while (numbers != 0) {
            count++;
            numbers /= 10;
        }
        return count;
    }
}
