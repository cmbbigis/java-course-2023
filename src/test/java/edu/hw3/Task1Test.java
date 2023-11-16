package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    void helloWorldAtbashEncryption() {
        final String expectedString = "Svool dliow!";

        var string = "Hello world!";
        var result = Task1.atbash(string);

        assertThat(result).isEqualTo(expectedString);
    }
}
