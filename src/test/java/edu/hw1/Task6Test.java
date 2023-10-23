package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @Test
    void alreadyEqualsNumber_Count_CorrectResult() {
        final int EXPECTED_COUNT = 1;

        int countK = Task6.countK(6174);
        assertThat(countK).isEqualTo(EXPECTED_COUNT);
    }

    @Test
    void desireNumberIsAttainable_Count_CorrectResult() {
        final int EXPECTED_COUNT = 3;

        int countK = Task6.countK(3524);
        assertThat(countK).isEqualTo(EXPECTED_COUNT);
    }

    @Test
    void desireNumberIsUnattainable_Count_CorrectResult() {
        final int EXPECTED_COUNT = -1;

        int countK = Task6.countK(6666);
        assertThat(countK).isEqualTo(EXPECTED_COUNT);
    }

}
