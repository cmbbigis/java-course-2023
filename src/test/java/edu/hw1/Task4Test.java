package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Digits")
    void test1() {
        String string = Task4.fixString("123456");

        assertThat(string).isEqualTo("214365");
    }

    @Test
    @DisplayName("Letters")
    void test2() {
        String string = Task4.fixString("hTsii  s aimex dpus rtni.g");

        assertThat(string).isEqualTo("This is a mixed up string.");
    }

    @Test
    @DisplayName("Not even")
    void test3() {
        String string = Task4.fixString("badce");

        assertThat(string).isEqualTo("abcde");
    }

    @Test
    @DisplayName("One char")
    void test4() {
        String string = Task4.fixString("b");

        assertThat(string).isEqualTo("b");
    }
}
