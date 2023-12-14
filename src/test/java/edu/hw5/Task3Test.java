package edu.hw5;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    void tomorrowParseDate() {
        final Optional<LocalDate> expectedResult = Optional.of(LocalDate.now().plusDays(1));

        var date = Task3.parseDate("tomorrow");

        assertThat(date).isEqualTo(expectedResult);
    }

    @Test
    void todayParseDate() {
        final Optional<LocalDate> expectedResult = Optional.of(LocalDate.now());

        var date = Task3.parseDate("today");

        assertThat(date).isEqualTo(expectedResult);
    }

    @Test
    void yesterdayParseDate() {
        final Optional<LocalDate> expectedResult = Optional.of(LocalDate.now().minusDays(1));

        var date = Task3.parseDate("yesterday");

        assertThat(date).isEqualTo(expectedResult);
    }

    @Test
    void dashSeparatedDateParseDate() {
        final Optional<LocalDate> expectedResult = Optional.of(LocalDate.of(2020, 10, 10));

        var date = Task3.parseDate("2020-10-10");

        assertThat(date).isEqualTo(expectedResult);
    }

    @Test
    void slashSeparatedDateParseDate() {
        final Optional<LocalDate> expectedResult = Optional.of(LocalDate.of(1976, 1, 3));

        var date = Task3.parseDate("1/3/1976");

        assertThat(date).isEqualTo(expectedResult);
    }

    @Test
    void slashSeparatedDateWithoutThousandsAndHundredsParseDate() {
        final Optional<LocalDate> expectedResult = Optional.of(LocalDate.of(2020, 1, 3));

        var date = Task3.parseDate("1/3/20");

        assertThat(date).isEqualTo(expectedResult);
    }

    @Test
    void dayAgoParseDate() {
        final Optional<LocalDate> expectedResult = Optional.of(LocalDate.now().minusDays(1));

        var date = Task3.parseDate("1 day ago");

        assertThat(date).isEqualTo(expectedResult);
    }

    @Test
    void daysAgoParseDate() {
        final Optional<LocalDate> expectedResult = Optional.of(LocalDate.now().minusDays(2));

        var date = Task3.parseDate("2 days ago");

        assertThat(date).isEqualTo(expectedResult);
    }

    @Test
    void notADateParseDate() {
        final Optional<LocalDate> expectedResult = Optional.empty();

        var date = Task3.parseDate("ashdashdhasd 123 askdajsdkasjd days ago");

        assertThat(date).isEqualTo(expectedResult);
    }
}
