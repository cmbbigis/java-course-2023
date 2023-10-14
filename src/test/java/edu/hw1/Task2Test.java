package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Four digits")
    void test1() {
        var count = Task2.countDigits(4666);

        assertThat(count).isEqualTo(4);
    }

    @Test
    @DisplayName("Three digits")
    void test2() {
        var count = Task2.countDigits(544);
        assertThat(count).isEqualTo(3);
    }

    @Test
    @DisplayName("Zero")
    void test3() {
        var count = Task2.countDigits(0);

        assertThat(count).isEqualTo(1);
    }
}
