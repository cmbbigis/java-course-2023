package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    @DisplayName("Max")
    void test1() {
        var result = Task3.isNestable(new int[] {1, 2, 3, 4}, new int[] {1, 6});

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Min")
    void test2() {
        var result = Task3.isNestable(new int[] {5, 1}, new int[] {4, 0});

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Max and min equals")
    void test3() {
        var result = Task3.isNestable(new int[] {9, 9, 8}, new int[] {8, 9});

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("All conditions not met")
    void test4() {
        var result = Task3.isNestable(new int[] {1, 2, 3, 4}, new int[] {2, 3});

        assertThat(result).isFalse();
    }
}
