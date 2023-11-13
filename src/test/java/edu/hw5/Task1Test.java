package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    void twoPeriodsCountAverage() {
        final String[] periods = new String[] {
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        };

        var averageDuration = Task1.countAverageDuration(periods);

        assertThat(averageDuration).isEqualTo("3ч 40м");
    }

    @Test
    void onePeriodCountAverage() {
        final String[] periods = new String[] {
            "2022-03-12, 20:20 - 2022-03-12, 23:50"
        };

        var averageDuration = Task1.countAverageDuration(periods);

        assertThat(averageDuration).isEqualTo("3ч 30м");
    }
}
