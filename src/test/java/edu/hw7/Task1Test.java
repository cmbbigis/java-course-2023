package edu.hw7;

import org.junit.jupiter.api.Test;
import java.util.stream.IntStream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    void testCounter() throws InterruptedException {
        Task1.Counter counter = new Task1.Counter();
        int numberOfThreads = 1000;
        Thread[] threads = new Thread[numberOfThreads];

        IntStream.range(0, numberOfThreads).forEach(i -> {
            threads[i] = new Thread(counter::increment);
            threads[i].start();
        });

        for (Thread thread : threads) {
            thread.join();
        }

        assertThat(counter.getCount()).isEqualTo(numberOfThreads);
    }
}
