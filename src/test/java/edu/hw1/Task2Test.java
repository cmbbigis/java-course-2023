package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    void fourDigitNumberCountDigitsFour() {
        final int EXPECTED_COUNT = 4;

        var count = Task2.countDigits(4666);
        assertThat(count).isEqualTo(EXPECTED_COUNT);
    }

    @Test
    void threeDigitNumberCountDigitsThree() {
        final int EXPECTED_COUNT = 3;

        var count = Task2.countDigits(544);
        assertThat(count).isEqualTo(EXPECTED_COUNT);
    }

    @Test
    void zeroCountDigitsOne() {
        final int EXPECTED_COUNT = 1;

        var count = Task2.countDigits(0);
        assertThat(count).isEqualTo(EXPECTED_COUNT);
    }
}
