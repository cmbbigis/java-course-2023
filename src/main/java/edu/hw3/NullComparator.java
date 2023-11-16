package edu.hw3;

import java.util.Comparator;

public final class NullComparator implements Comparator<String> {
    public NullComparator() {
    }

    @Override
    public int compare(String o1, String o2) {
        if (o1 != null && o2 != null) {
            return o1.compareTo(o2);
        } else if (o1 == null && o2 != null) {
            return -1;
        } else if (o1 != null) {
            return 1;
        } else {
            return 0;
        }
    }
}
