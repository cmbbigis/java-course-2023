package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    void secondMaxBiggerThanFirstMax_Check_True() {
        var result = Task3.isNestable(new int[] {1, 2, 3, 4}, new int[] {1, 6});
        assertThat(result).isTrue();
    }

    @Test
    void firstMinBiggerThanSecondMin_Check_True() {
        var result = Task3.isNestable(new int[] {5, 1}, new int[] {4, 0});
        assertThat(result).isTrue();
    }

    @Test
    void maxAndMinEquals_Check_False() {
        var result = Task3.isNestable(new int[] {9, 9, 8}, new int[] {8, 9});
        assertThat(result).isFalse();
    }

    @Test
    void allConditionsNotMet_Check_False() {
        var result = Task3.isNestable(new int[] {1, 2, 3, 4}, new int[] {2, 3});
        assertThat(result).isFalse();
    }
}
