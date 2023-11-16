package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    void numberWithOnlyUnitsConvertToRoman() {
        final String expectedRoman = "II";

        var number = 2;
        var result = Task4.convertToRoman(number);

        assertThat(result).isEqualTo(expectedRoman);
    }

    @Test
    void numberWithTensConvertToRoman() {
        final String expectedRoman = "XXVIII";

        var number = 28;
        var result = Task4.convertToRoman(number);

        assertThat(result).isEqualTo(expectedRoman);
    }

    @Test
    void numberWithHundredsConvertToRoman() {
        final String expectedRoman = "DCXXVIII";

        var number = 628;
        var result = Task4.convertToRoman(number);

        assertThat(result).isEqualTo(expectedRoman);
    }

    @Test
    void numberWithThousandsToRoman() {
        final String expectedRoman = "MMDCXXVIII";

        var number = 2628;
        var result = Task4.convertToRoman(number);

        assertThat(result).isEqualTo(expectedRoman);
    }
}
