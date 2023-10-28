package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    void HelloWorldAtbashEncryptionCorrectResult() {
        final String EXPECTED_STRING = "Svool dliow!";

        var string = "Hello world!";
        var result = Task1.atbash(string);

        assertThat(result).isEqualTo(EXPECTED_STRING);
    }
}
