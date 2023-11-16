package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StockMarketTest {
    @Test
    void emptyMarketAdd() {
        final int expectedCost = 1;

        var market = new StockMarket();
        market.add(new Stock(1));

        assertThat(market.mostValuableStock().cost).isEqualTo(expectedCost);
    }

    @Test
    void marketDelete() {
        final int expectedCost = 1;

        var market = new StockMarket();
        market.add(new Stock(1));
        var stockToDelete = new Stock(2);
        market.add(stockToDelete);
        market.add(new Stock(1));
        market.remove(stockToDelete);

        assertThat(market.mostValuableStock().cost).isEqualTo(expectedCost);
    }

    @Test
    void marketGetMostValuableStock() {
        final int expectedCost = 2;

        var market = new StockMarket();
        market.add(new Stock(1));
        market.add(new Stock(2));
        market.add(new Stock(1));

        assertThat(market.mostValuableStock().cost).isEqualTo(expectedCost);
    }
}
