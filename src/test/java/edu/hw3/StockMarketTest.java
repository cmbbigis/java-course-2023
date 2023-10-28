package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StockMarketTest {
    @Test
    void emptyMarketAddCorrectResult() {
        final int EXPECTED_COST = 1;

        var market = new StockMarket();
        market.add(new Stock(1));

        assertThat(market.mostValuableStock().cost).isEqualTo(EXPECTED_COST);
    }

    @Test
    void marketDeleteCorrectResult() {
        final int EXPECTED_COST = 1;

        var market = new StockMarket();
        market.add(new Stock(1));
        var stockToDelete = new Stock(2);
        market.add(stockToDelete);
        market.add(new Stock(1));
        market.remove(stockToDelete);

        assertThat(market.mostValuableStock().cost).isEqualTo(EXPECTED_COST);
    }

    @Test
    void marketGetMostValuableStockCorrectResult() {
        final int EXPECTED_COST = 2;

        var market = new StockMarket();
        market.add(new Stock(1));
        market.add(new Stock(2));
        market.add(new Stock(1));

        assertThat(market.mostValuableStock().cost).isEqualTo(EXPECTED_COST);
    }
}
