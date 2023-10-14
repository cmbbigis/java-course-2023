package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    @DisplayName("Simple")
    void test1() {
        var result = Task5.isPalindromeDescendant(11);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Descendant is palindrome")
    void test2() {
        var result = Task5.isPalindromeDescendant(11211230);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Not palindrome")
    void test3() {
        var result = Task5.isPalindromeDescendant(12);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Descendant also not palindrome")
    void test4() {
        var result = Task5.isPalindromeDescendant(1231);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("One digit")
    void test5() {
        var result = Task5.isPalindromeDescendant(1);

        assertThat(result).isFalse();
    }
}
