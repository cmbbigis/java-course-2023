package edu.hw7;

import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.LongStream;

public final class Task2 {
    private Task2() {
    }

    public static AtomicLong factorial(int n) {
        AtomicLong result = new AtomicLong(1);
        LongStream.rangeClosed(1, n)
            .parallel()
            .forEach(i -> result.updateAndGet(v -> v * i));
        return result;
    }
}
