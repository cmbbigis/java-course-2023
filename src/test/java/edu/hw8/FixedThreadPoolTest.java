package edu.hw8;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FixedThreadPoolTest {
    @Test
    public void testFibonacci() throws InterruptedException {
        int numTasks = 10;
        CountDownLatch latch = new CountDownLatch(numTasks);
        AtomicInteger counter = new AtomicInteger(0);

        FixedThreadPool threadPool = FixedThreadPool.create(4);
        threadPool.start();

        IntStream.range(0, numTasks).forEach(i -> threadPool.execute(() -> {
            int fib = fibonacci(i);
            System.out.println("Fibonacci of " + i + " is " + fib);
            counter.incrementAndGet();
            latch.countDown();
        }));

        latch.await();
        threadPool.close();

        assertThat(counter.get()).isEqualTo(numTasks);
    }

    private int fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
