package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    void validStringCheckPattern1() {
        final String string = "1101";

        var isMatches = Task7.isMatchesPattern1(string);

        assertThat(isMatches).isTrue();
    }

    @Test
    void invalidStringCheckPattern1() {
        final String string = "101";

        var isMatches = Task7.isMatchesPattern1(string);

        assertThat(isMatches).isFalse();
    }

    @Test
    void validStringCheckPattern2() {
        final String string = "1001";

        var isMatches = Task7.isMatchesPattern2(string);

        assertThat(isMatches).isTrue();
    }

    @Test
    void invalidStringCheckPattern2() {
        final String string = "1000";

        var isMatches = Task7.isMatchesPattern2(string);

        assertThat(isMatches).isFalse();
    }

    @Test
    void validStringCheckPattern3() {
        final String string = "10";

        var isMatches = Task7.isLengthAtLeast1AndNoMoreThan3(string);

        assertThat(isMatches).isTrue();
    }

    @Test
    void invalidStringCheckPattern3() {
        final String string = "1000";

        var isMatches = Task7.isLengthAtLeast1AndNoMoreThan3(string);

        assertThat(isMatches).isFalse();
    }
}
