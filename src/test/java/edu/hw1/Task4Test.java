package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    void stringWithDigits_ReverseString_CorrectResult() {
        final String EXPECTED_STRING = "214365";

        String string = Task4.fixString("123456");
        assertThat(string).isEqualTo(EXPECTED_STRING);
    }

    @Test
    void stringWithLetters_ReverseString_CorrectResult() {
        final String EXPECTED_STRING = "This is a mixed up string.";

        String string = Task4.fixString("hTsii  s aimex dpus rtni.g");
        assertThat(string).isEqualTo(EXPECTED_STRING);
    }

    @Test
    void stringWithNotEvenCharsCount_ReverseString_CorrectResult() {
        final String EXPECTED_STRING = "abcde";

        String string = Task4.fixString("badce");
        assertThat(string).isEqualTo(EXPECTED_STRING);
    }

    @Test
    void stringWithOneChar_ReverseString_CorrectResult() {
        final String EXPECTED_STRING = "b";

        String string = Task4.fixString("b");
        assertThat(string).isEqualTo(EXPECTED_STRING);
    }
}
