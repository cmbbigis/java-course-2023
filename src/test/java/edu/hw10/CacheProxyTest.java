package edu.hw10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CacheProxyTest
{
    public interface FibCalculator {
        @Cache(persist = true)
        long fib(int number);
    }

    private FibCalculator proxyCalculator;
    private List<String> proxyList;

    @BeforeEach
    public void setUp() {
        FibCalculator calculator = new FibCalculator() {
            @Override
            public long fib(int number) {
                if (number <= 1) return number;
                else return fib(number - 1) + fib(number - 2);
            }
        };
        proxyCalculator = CacheProxy.create(calculator, FibCalculator.class);

        List<String> list = new ArrayList<>();
        proxyList = CacheProxy.create(list, List.class);
    }

    @Test
    public void fibCheck() {
        long result1 = proxyCalculator.fib(10);
        long result2 = proxyCalculator.fib(10);
        assertThat(result1).isEqualTo(result2);
    }

    @Test
    public void listCheck() {
        proxyList.add("test");
        String result1 = proxyList.get(0);
        String result2 = proxyList.get(0);
        assertThat(result1).isEqualTo(result2);
    }
}
