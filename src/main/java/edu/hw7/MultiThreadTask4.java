package edu.hw7;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public final class MultiThreadTask4 {
    private MultiThreadTask4() {
    }

    private static final double FOUR = 4.0;

    public static double calculatePi(int iterationsCount) throws InterruptedException {
        AtomicInteger circleCount = new AtomicInteger(0);
        int numThreads = Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                int localCircleCount = 0;
                for (int j = 0; j < iterationsCount / numThreads; j++) {
                    double x = ThreadLocalRandom.current().nextDouble();
                    double y = ThreadLocalRandom.current().nextDouble();

                    if (x * x + y * y <= 1) {
                        localCircleCount++;
                    }
                }
                circleCount.addAndGet(localCircleCount);
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        return FOUR * circleCount.get() / iterationsCount;
    }
}
