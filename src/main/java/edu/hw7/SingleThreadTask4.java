package edu.hw7;

import java.util.Random;

public final class SingleThreadTask4 {
    private SingleThreadTask4() {
    }

    private static final double FOUR = 4.0;

    public static double calculatePi(int iterationsCount) {
        int circleCount = 0;
        Random random = new Random();

        for (int i = 0; i < iterationsCount; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();

            if (x * x + y * y <= 1) {
                circleCount++;
            }
        }

        return FOUR * circleCount / iterationsCount;
    }
}
