package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Minute without seconds")
    void test1() {
        var seconds = Task1.minutesToSeconds("01:00");

        assertThat(seconds).isEqualTo(60);
    }

    @Test
    @DisplayName("Minutes with seconds")
    void test2() {
        var seconds = Task1.minutesToSeconds("13:56");
        assertThat(seconds).isEqualTo(836);
    }

    @Test
    @DisplayName("Only seconds")
    void test3() {
        var seconds = Task1.minutesToSeconds("00:56");
        assertThat(seconds).isEqualTo(56);
    }

    @Test
    @DisplayName("Invalid input")
    void test4() {
        var seconds = Task1.minutesToSeconds("10:60");

        assertThat(seconds).isEqualTo(-1);
    }
}
