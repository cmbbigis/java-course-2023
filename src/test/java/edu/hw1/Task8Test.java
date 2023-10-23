package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {
    @Test
    void isCapture_Check_True() {
        boolean isCapture = Task8.knightBoardCapture(new int[][] {
            new int[] {0, 0, 0, 1, 0, 0, 0, 0},
            new int[] {0, 0, 0, 0, 0, 0, 0, 0},
            new int[] {0, 1, 0, 0, 0, 1, 0, 0},
            new int[] {0, 0, 0, 0, 1, 0, 1, 0},
            new int[] {0, 1, 0, 0, 0, 1, 0, 0},
            new int[] {0, 0, 0, 0, 0, 0, 0, 0},
            new int[] {0, 1, 0, 0, 0, 0, 0, 1},
            new int[] {0, 0, 0, 0, 1, 0, 0, 0}
        });
        assertThat(isCapture).isTrue();
    }

    @Test
    void isNotCapture_Check_False() {
        boolean isCapture = Task8.knightBoardCapture(new int[][] {
            new int[] {1, 0, 1, 0, 1, 0, 1, 0},
            new int[] {0, 1, 0, 1, 0, 1, 0, 1},
            new int[] {0, 0, 0, 0, 1, 0, 1, 0},
            new int[] {0, 0, 1, 0, 0, 1, 0, 1},
            new int[] {1, 0, 0, 0, 1, 0, 1, 0},
            new int[] {0, 0, 0, 0, 0, 1, 0, 1},
            new int[] {1, 0, 0, 0, 1, 0, 1, 0},
            new int[] {0, 0, 0, 1, 0, 1, 0, 1}
        });
        assertThat(isCapture).isFalse();
    }
}
