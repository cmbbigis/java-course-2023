package edu.hw3;

import java.util.ArrayList;

public final class Task2 {
    private Task2() {
    }

    public static ArrayList<String> clusterize(String string) {
        var result = new ArrayList<String>();
        var sb = new StringBuilder();
        var count = 0;
        for (var i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '(') {
                count++;
            } else {
                count--;
            }
            sb.append(string.charAt(i));
            if (count == 0) {
                result.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        return result;
    }
}
