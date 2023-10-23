package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    void eight_Rotate_CorrectResult() {
        final int EXPECTED_NUMBER = 4;

        int number = Task7.rotateRight(8, 1);
        assertThat(number).isEqualTo(EXPECTED_NUMBER);
    }

    @Test
    void sixteen_Rotate_CorrectResult() {
        final int EXPECTED_NUMBER = 1;

        int number = Task7.rotateLeft(16, 1);
        assertThat(number).isEqualTo(EXPECTED_NUMBER);
    }

    @Test
    void seventeen_Rotate_CorrectResult() {
        final int EXPECTED_NUMBER = 6;

        int number = Task7.rotateLeft(17, 2);
        assertThat(number).isEqualTo(EXPECTED_NUMBER);
    }
}
