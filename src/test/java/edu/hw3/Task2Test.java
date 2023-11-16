package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    void simpleStringClusterize() {
        final List<String> expectedResult = List.of("()", "()", "()");

        var string = "()()()";
        var result = Task2.clusterize(string);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void nestedBracketsClusterize() {
        final List<String> expectedResult = List.of("((()))");

        var string = "((()))";
        var result = Task2.clusterize(string);

        assertThat(result).isEqualTo(expectedResult);
    }
}
