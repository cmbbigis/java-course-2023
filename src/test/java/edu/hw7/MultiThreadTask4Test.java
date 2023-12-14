package edu.hw7;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;

public class MultiThreadTask4Test {
    @Test
    public void testCalculatePi() throws InterruptedException {
        double pi = MultiThreadTask4.calculatePi(10000000);
        assertThat(pi).isCloseTo(3.14, within(0.01));
    }
}
