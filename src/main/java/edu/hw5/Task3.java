package edu.hw5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public final class Task3 {
    private Task3() {
    }

    private static final DateTimeFormatter[] FORMATTERS = {
        DateTimeFormatter.ofPattern("yyyy-M-d"),
        DateTimeFormatter.ofPattern("M/d/yyyy"),
        DateTimeFormatter.ofPattern("M/d/yy")
    };

    public static Optional<LocalDate> parseDate(String string) {
        LocalDate date = null;

        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                date = LocalDate.parse(string, formatter);
                break;
            } catch (DateTimeParseException ignored) {
            }
        }

        if (date == null) {
            if (string.equalsIgnoreCase("tomorrow")) {
                date = LocalDate.now().plusDays(1);
            } else if (string.equalsIgnoreCase("today")) {
                date = LocalDate.now();
            } else if (string.equalsIgnoreCase("yesterday") || string.equals("1 day ago")) {
                date = LocalDate.now().minusDays(1);
            } else if (string.matches("\\d days ago")) {
                try {
                    var days = Integer.parseInt(string.substring(0, string.indexOf(" ")));
                    date = LocalDate.now().minusDays(days);
                } catch (NumberFormatException ignored) {
                }
            }
        }

        return Optional.ofNullable(date);
    }
}
