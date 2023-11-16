package edu.hw3;

public final class Stock implements Comparable<Stock> {
    public Stock(int cost) {
        this.cost = cost;
    }

    @Override
    public int compareTo(Stock other) {
        return Integer.compare(cost, other.cost);
    }

    public final int cost;
}
