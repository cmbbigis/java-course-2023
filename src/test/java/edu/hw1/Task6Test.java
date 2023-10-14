package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @Test
    @DisplayName("Simple")
    void test1() {
        int countK = Task6.countK(6174);

        assertThat(countK).isEqualTo(1);
    }

    @Test
    @DisplayName("Hard")
    void test2() {
        int countK = Task6.countK(3524);
        assertThat(countK).isEqualTo(3);
    }

    @Test
    @DisplayName("Not reachable")
    void test3() {
        int countK = Task6.countK(6666);

        assertThat(countK).isEqualTo(-1);
    }


}
