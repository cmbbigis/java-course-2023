package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    @DisplayName("Right")
    void test1() {
        int minute = Task7.rotateRight(8, 1);

        assertThat(minute).isEqualTo(4);
    }

    @Test
    @DisplayName("Left")
    void test2() {
        int minute = Task7.rotateLeft(16, 1);

        assertThat(minute).isEqualTo(1);
    }

    @Test
    @DisplayName("Left two steps")
    void test3() {
        int minute = Task7.rotateLeft(17, 2);

        assertThat(minute).isEqualTo(6);
    }
}
