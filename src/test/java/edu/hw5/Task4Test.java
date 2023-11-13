package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    void invalidPasswordCheck() {
        final String password = "password";

        var isValid = Task4.isPasswordValid(password);

        assertThat(isValid).isFalse();
    }

    @Test
    void validPasswordCheck() {
        final String password = "password!";

        var isValid = Task4.isPasswordValid(password);

        assertThat(isValid).isTrue();
    }
}
