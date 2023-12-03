package edu.hw8;

import org.junit.jupiter.api.Test;
import java.util.stream.IntStream;

public class FixedThreadPoolTest {
    @Test
    public void testFibonacci() {
        FixedThreadPool threadPool = new FixedThreadPool(4);
        threadPool.start();

        IntStream.range(0, 10).forEach(i -> threadPool.execute(() -> {
            int fib = fibonacci(i);
            System.out.println("Fibonacci of " + i + " is " + fib);
        }));

        threadPool.close();
    }

    private int fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
