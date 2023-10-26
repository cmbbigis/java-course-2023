package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    void palindromeNumberCheckTrue() {
        var result = Task5.isPalindromeDescendant(11);
        assertThat(result).isTrue();
    }

    @Test
    void numberWithPalindromeDescendantCheckTrue() {
        var result = Task5.isPalindromeDescendant(11211230);
        assertThat(result).isTrue();
    }

    @Test
    void notPalindromeNumberCheckFalse() {
        var result = Task5.isPalindromeDescendant(12);
        assertThat(result).isFalse();
    }

    @Test
    void numberWithNotPalindromeDescendantCheckFalse() {
        var result = Task5.isPalindromeDescendant(1231);
        assertThat(result).isFalse();
    }

    @Test
    void oneDigitNumberCheckFalse() {
        var result = Task5.isPalindromeDescendant(1);
        assertThat(result).isFalse();
    }
}
