package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @Test
    void subsequenceCheck() {
        final String sequence = "achfdbaabgabcaabg";
        final String subsequence = "abc";

        var isSubsequence = Task6.isSubsequence(subsequence, sequence);

        assertThat(isSubsequence).isTrue();
    }

    @Test
    void notSubsequenceCheck() {
        final String sequence = "afbc";
        final String subsequence = "abc";

        var isSubsequence = Task6.isSubsequence(subsequence, sequence);

        assertThat(isSubsequence).isFalse();
    }
}
