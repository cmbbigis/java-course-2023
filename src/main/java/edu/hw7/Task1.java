package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;

public final class Task1 {

    private Task1() {
    }

    public static class Counter {
        private final AtomicInteger count = new AtomicInteger(0);

        public void increment() {
            count.incrementAndGet();
        }

        public int getCount() {
            return count.get();
        }
    }
}
