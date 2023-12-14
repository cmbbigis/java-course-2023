package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.util.ArrayList;
import java.util.List;

public final class Task2 {
    private Task2() {
    }

    static final int MONTHS_IN_YEAR = 12;
    static final int DAY_OF_FRIDAY = 13;

    public static List<LocalDate> findFridays13(int year) {
        List<LocalDate> fridays13 = new ArrayList<>();
        for (int month = 1; month <= MONTHS_IN_YEAR; month++) {
            LocalDate date = LocalDate.of(year, month, DAY_OF_FRIDAY);
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridays13.add(date);
            }
        }
        return fridays13;
    }

    public static LocalDate nextFriday13(LocalDate date) {
        TemporalAdjuster nextFriday13Adjuster = temporal -> {
            LocalDate tempDate = (LocalDate) temporal;
            do {
                tempDate = tempDate.plusMonths(1).withDayOfMonth(DAY_OF_FRIDAY);
            } while (tempDate.getDayOfWeek() != DayOfWeek.FRIDAY);
            return tempDate;
        };
        return date.with(nextFriday13Adjuster);
    }
}
