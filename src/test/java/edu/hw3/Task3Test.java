package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    void arrayMakeFreqDict() {
        final HashMap<String, Integer> expectedHashmap = new HashMap<>() {{
            put("bb", 2);
            put("a", 2);
        }};

        var strings = new String[] {"a", "bb", "a", "bb"};
        var result = Task3.freqDict(strings);

        assertThat(result).isEqualTo(expectedHashmap);
    }
}
