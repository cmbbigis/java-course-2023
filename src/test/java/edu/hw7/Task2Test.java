package edu.hw7;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    void numberCalculateFactorial() {
        assertThat(Task2.factorial(5).get()).isEqualTo(120);
    }

    @Test
    void zeroCalculateFactorial() {
        assertThat(Task2.factorial(0).get()).isEqualTo(1);
    }

    @Test
    void oneCalculateFactorial() {
        assertThat(Task2.factorial(1).get()).isEqualTo(1);
    }
}
