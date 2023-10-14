package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {
    @Test
    @DisplayName("True")
    void test1() {
        boolean minute = Task8.knightBoardCapture(new int[][] {
            new int[] {0, 0, 0, 1, 0, 0, 0, 0},
            new int[] {0, 0, 0, 0, 0, 0, 0, 0},
            new int[] {0, 1, 0, 0, 0, 1, 0, 0},
            new int[] {0, 0, 0, 0, 1, 0, 1, 0},
            new int[] {0, 1, 0, 0, 0, 1, 0, 0},
            new int[] {0, 0, 0, 0, 0, 0, 0, 0},
            new int[] {0, 1, 0, 0, 0, 0, 0, 1},
            new int[] {0, 0, 0, 0, 1, 0, 0, 0}
        });

        assertThat(minute).isTrue();
    }

    @Test
    @DisplayName("False")
    void test2() {
        boolean minute = Task8.knightBoardCapture(new int[][] {
            new int[] {1, 0, 1, 0, 1, 0, 1, 0},
            new int[] {0, 1, 0, 1, 0, 1, 0, 1},
            new int[] {0, 0, 0, 0, 1, 0, 1, 0},
            new int[] {0, 0, 1, 0, 0, 1, 0, 1},
            new int[] {1, 0, 0, 0, 1, 0, 1, 0},
            new int[] {0, 0, 0, 0, 0, 1, 0, 1},
            new int[] {1, 0, 0, 0, 1, 0, 1, 0},
            new int[] {0, 0, 0, 1, 0, 1, 0, 1}
        });
        assertThat(minute).isFalse();
    }
}
