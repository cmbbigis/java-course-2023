package edu.hw1;

public final class Task1 {
    private Task1() {
    }

    public static int minutesToSeconds(String time) {
        var timeArray = time.split(":");
        var minutes = Integer.parseInt(timeArray[0]);
        var seconds = Integer.parseInt(timeArray[1]);
        if (seconds >= 60) {
            return -1;
        }
        return minutes * 60 + seconds;
    }
}
