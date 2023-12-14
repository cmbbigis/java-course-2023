package edu.hw5;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    void yearFindFridays13() {
        final List<LocalDate> expectedResult = List.of(
            LocalDate.of(1925, 2, 13),
            LocalDate.of(1925, 3, 13),
            LocalDate.of(1925, 11, 13));
        final int year = 1925;

        var fridays = Task2.findFridays13(year);

        assertThat(fridays).isEqualTo(expectedResult);
    }

    @Test
    void dateFindNextFriday13() {
        final LocalDate expectedResult = LocalDate.of(2024, 12, 13);

        var date = LocalDate.of(2024, 9, 14);
        var nextFriday13Date = Task2.nextFriday13(date);

        assertThat(nextFriday13Date).isEqualTo(expectedResult);
    }
}
