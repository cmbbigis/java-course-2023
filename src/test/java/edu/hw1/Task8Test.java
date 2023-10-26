package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {
    @Test
    void isCaptureCheckTrue() {
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
    void isNotCaptureCheckFalse() {
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
