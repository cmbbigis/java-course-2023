package edu.hw3;

import java.util.Collections;
import java.util.PriorityQueue;

public final class StockMarket {
    private final PriorityQueue<Stock> stocks;

    public StockMarket() {
        this.stocks = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void add(Stock stock) {
        stocks.add(stock);
    }

    public void remove(Stock stock) {
        stocks.remove(stock);
    }


    public Stock mostValuableStock() {
        return stocks.peek();
    }
}
