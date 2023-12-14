package edu.hw7;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;

public class SingleThreadTask4Test {
    @Test
    public void testCalculatePi() {
        double pi = SingleThreadTask4.calculatePi(10000000);
        assertThat(pi).isCloseTo(3.14, within(0.01));
    }
}
