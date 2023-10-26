package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    void minuteWithoutSecondsMinutesToSecondsCorrectResult() {
        final int EXPECTED_SECONDS = 60;

        var seconds = Task1.minutesToSeconds("01:00");
        assertThat(seconds).isEqualTo(EXPECTED_SECONDS);
    }

    @Test
    void minutesWithSecondsMinutesToSecondsCorrectResult() {
        final int EXPECTED_SECONDS = 836;

        var seconds = Task1.minutesToSeconds("13:56");
        assertThat(seconds).isEqualTo(EXPECTED_SECONDS);
    }

    @Test
    void onlySecondsMinutesToSecondsCorrectResult() {
        final int EXPECTED_SECONDS = 56;

        var seconds = Task1.minutesToSeconds("00:56");
        assertThat(seconds).isEqualTo(EXPECTED_SECONDS);
    }

    @Test
    void invalidInputMinutesToSecondsCorrectResult() {
        final int EXPECTED_SECONDS = -1;

        var seconds = Task1.minutesToSeconds("10:60");
        assertThat(seconds).isEqualTo(EXPECTED_SECONDS);
    }
}
