package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    void numberWithOnlyUnitsConvertToRomanCorrectResult() {
        final String EXPECTED_ROMAN = "II";

        var number = 2;
        var result = Task4.convertToRoman(number);

        assertThat(result).isEqualTo(EXPECTED_ROMAN);
    }

    @Test
    void numberWithTensConvertToRomanCorrectResult() {
        final String EXPECTED_ROMAN = "XXVIII";

        var number = 28;
        var result = Task4.convertToRoman(number);

        assertThat(result).isEqualTo(EXPECTED_ROMAN);
    }

    @Test
    void numberWithHundredsConvertToRomanCorrectResult() {
        final String EXPECTED_ROMAN = "DCXXVIII";

        var number = 628;
        var result = Task4.convertToRoman(number);

        assertThat(result).isEqualTo(EXPECTED_ROMAN);
    }

    @Test
    void numberWithThousandsToRomanCorrectResult() {
        final String EXPECTED_ROMAN = "MMDCXXVIII";

        var number = 2628;
        var result = Task4.convertToRoman(number);

        assertThat(result).isEqualTo(EXPECTED_ROMAN);
    }
}
