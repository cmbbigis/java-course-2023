package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BackwardIteratorTest {
    @Test
    void iteratorCheckThatWorkCorrect() {
        var backwardIterator = new BackwardIterator<>(List.of(1,2,3));

        assertThat(backwardIterator.next()).isEqualTo(3);
        assertThat(backwardIterator.next()).isEqualTo(2);
        assertThat(backwardIterator.next()).isEqualTo(1);
        assertThat(backwardIterator.hasNext()).isFalse();
    }
}
