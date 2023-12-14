package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class Task1 {
    private Task1() {
    }

    public static String countAverageDuration(String[] periods) {
        List<Duration> durations = new ArrayList<>();
        for (String period : periods) {
            var dateTimes = period.split(" - ");
            var from = stringToDateTime(dateTimes[0]);
            var to = stringToDateTime(dateTimes[1]);
            durations.add(Duration.between(from, to));
        }
        Duration total = Duration.ZERO;
        for (Duration duration : durations) {
            total = total.plus(duration);
        }
        Duration average = total.dividedBy(durations.size());
        long hours = average.toHours();
        long minutes = average.minusHours(hours).toMinutes();
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
