package edu.hw3;

import java.util.Iterator;
import java.util.List;

public final class BackwardIterator<T> implements Iterator<T> {
    private final List<T> list;
    private int current;

    public BackwardIterator(List<T> list) {
        this.list = list;
        current = list.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return current >= 0;
    }

    @Override
    public T next() {
        return list.get(current--);
    }
}
