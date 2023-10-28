package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    void simpleStringClusterizeCorrectResult() {
        final List<String> EXPECTED_RESULT = List.of("()", "()", "()");

        var string = "()()()";
        var result = Task2.clusterize(string);

        assertThat(result).isEqualTo(EXPECTED_RESULT);
    }
}
