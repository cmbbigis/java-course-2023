package edu.hw3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public final class Task5 {
    private Task5() {
    }

    public static Object[] parseContacts(String[] contacts, String sorting) {
        var comparator = new ContactsComparator();
        if (Objects.equals(sorting, "ASC")) {
            Arrays.sort(contacts, comparator);
        } else {
            Arrays.sort(contacts, comparator.reversed());
        }
        return contacts;
    }

    private static class ContactsComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            var splitA = a.split(" ");
            var splitB = b.split(" ");
            if (splitA.length == 2 && splitB.length == 2) {
                return splitA[1].compareTo(splitB[1]);
            } else {
                return splitA[0].compareTo(splitB[0]);
            }
        }
    }
}
