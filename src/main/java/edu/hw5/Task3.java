package edu.hw5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public final class Task3 {
    private Task3() {
    }

    public static Optional<LocalDate> parseDate(String string) {
        DateTimeFormatter[] formatters = {
            DateTimeFormatter.ofPattern("yyyy-M-d"),
            DateTimeFormatter.ofPattern("M/d/yyyy"),
            DateTimeFormatter.ofPattern("M/d/yy")
        };
        LocalDate date = null;

        for (DateTimeFormatter formatter : formatters) {
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
            } else if (string.equalsIgnoreCase("yesterday") || string.endsWith(" day ago")) {
                date = LocalDate.now().minusDays(1);
            } else if (string.endsWith(" days ago")) {
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
