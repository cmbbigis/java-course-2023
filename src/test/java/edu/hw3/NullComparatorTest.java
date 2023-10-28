package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NullComparatorTest {
    @Test
    void emptyMapAddNullTrue() {
        var nullComparator = new NullComparator();
        TreeMap<String, String> tree = new TreeMap<>(nullComparator);
        tree.put(null, "test");
        tree.put("test", "test");
        tree.put("test1", null);

        assertThat(tree.containsKey(null)).isTrue();
    }
}
