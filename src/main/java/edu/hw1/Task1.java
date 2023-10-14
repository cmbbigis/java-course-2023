package edu.hw1;

public final class Task1 {
    private Task1() {
    }

    final private static int SECONDS_IN_MINUTE = 60;

    public static int minutesToSeconds(String time) {
        var timeArray = time.split(":");
        var minutes = Integer.parseInt(timeArray[0]);
        var seconds = Integer.parseInt(timeArray[1]);
        if (seconds >= SECONDS_IN_MINUTE) {
            return -1;
        }
        return minutes * SECONDS_IN_MINUTE + seconds;
    }
}
