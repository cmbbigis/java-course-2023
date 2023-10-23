package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    void palindromeNumber_Check_True() {
        var result = Task5.isPalindromeDescendant(11);
        assertThat(result).isTrue();
    }

    @Test
    void numberWithPalindromeDescendant_Check_True() {
        var result = Task5.isPalindromeDescendant(11211230);
        assertThat(result).isTrue();
    }

    @Test
    void notPalindromeNumber_Check_False() {
        var result = Task5.isPalindromeDescendant(12);
        assertThat(result).isFalse();
    }

    @Test
    void numberWithNotPalindromeDescendant_Check_False() {
        var result = Task5.isPalindromeDescendant(1231);
        assertThat(result).isFalse();
    }

    @Test
    void oneDigitNumber_Check_False() {
        var result = Task5.isPalindromeDescendant(1);
        assertThat(result).isFalse();
    }
}
