package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {
    @Test
    void validStringCheckPattern1() {
        final String string = "101";

        var isMatches = Task8.isMatchesPattern1(string);

        assertThat(isMatches).isTrue();
    }

    @Test
    void invalidStringCheckPattern1() {
        final String string = "1010";

        var isMatches = Task8.isMatchesPattern1(string);

        assertThat(isMatches).isFalse();
    }
    @Test
    void validStringCheckPattern2() {
        final String string = "0101";

        var isMatches = Task8.isMatchesPattern2(string);

        assertThat(isMatches).isTrue();
    }

    @Test
    void anotherValidStringCheckPattern2() {
        final String string = "0";

        var isMatches = Task8.isMatchesPattern2(string);

        assertThat(isMatches).isTrue();
    }

    @Test
    void invalidStringCheckPattern2() {
        final String string = "1101";

        var isMatches = Task8.isMatchesPattern2(string);

        assertThat(isMatches).isFalse();
    }

    @Test
    void validStringCheckPattern3() {
        final String string = "000";

        var isMatches = Task8.isMatchesPattern3(string);

        assertThat(isMatches).isTrue();
    }

    @Test
    void invalidStringCheckPattern3() {
        final String string = "00100";

        var isMatches = Task8.isMatchesPattern3(string);

        assertThat(isMatches).isFalse();
    }

    @Test
    void validStringCheckPattern4() {
        final String string = "101";

        var isMatches = Task8.isMatchesPattern4(string);

        assertThat(isMatches).isTrue();
    }

    @Test
    void invalidStringCheckPattern4() {
        final String string = "11";

        var isMatches = Task8.isMatchesPattern4(string);

        assertThat(isMatches).isFalse();
    }

    @Test
    void validStringCheckPattern5() {
        final String string = "101";

        var isMatches = Task8.isMatchesPattern5(string);

        assertThat(isMatches).isTrue();
    }

    @Test
    void invalidStringCheckPattern5() {
        final String string = "100";

        var isMatches = Task8.isMatchesPattern5(string);

        assertThat(isMatches).isFalse();
    }

    @Test
    void validStringCheckPattern6() {
        final String string = "100";

        var isMatches = Task8.isMatchesPattern6(string);

        assertThat(isMatches).isTrue();
    }

    @Test
    void invalidStringCheckPattern6() {
        final String string = "101";

        var isMatches = Task8.isMatchesPattern6(string);

        assertThat(isMatches).isFalse();
    }
}
