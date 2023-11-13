package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public final class Task1 {
    private Task1() {
    }

    static final int MINUTES_IN_HOUR = 60;

    public static String countAverageDuration(String[] periods) {
        var durations = new ArrayList<Long>();
        for (String period : periods) {
            var dateTimes = period.split(" - ");
            var from = stringToDateTime(dateTimes[0]);
            var to = stringToDateTime(dateTimes[1]);
            durations.add(Duration.between(from, to).toMinutes());
        }
        var average = durations.stream()
            .mapToDouble(l -> l)
            .average()
            .orElse(0.0);
        var hours = (int) (average / MINUTES_IN_HOUR);
        var minutes = (int) (average % MINUTES_IN_HOUR);
        return String.format("%dч %dм", hours, minutes);
    }

    private static LocalDateTime stringToDateTime(String dateTime) {
        var dateAndTime = dateTime.split(", ");
        var date = dateAndTime[0].split("-");
        var time = dateAndTime[1].split(":");
        return LocalDateTime.of(Integer.parseInt(date[0]),
            Integer.parseInt(date[1]),
            Integer.parseInt(date[2]),
            Integer.parseInt(time[0]),
            Integer.parseInt(time[1]));
    }
}
