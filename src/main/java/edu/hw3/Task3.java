package edu.hw3;

import java.util.HashMap;

public final class Task3 {
    private Task3() {
    }

    public static HashMap<String, Integer> freqDict(String[] numbers) {
       var dictionary = new HashMap<String, Integer>();
        for (String number : numbers) {
            if (dictionary.containsKey(number)) {
                dictionary.replace(number, dictionary.get(number), dictionary.get(number) + 1);
            } else {
                dictionary.put(number, 1);
            }
        }
       return dictionary;
    }
}
