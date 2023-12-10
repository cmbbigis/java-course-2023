package edu.hw9;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FileSearcherTest {
    @Test
    public void directoryWithFileSearch() {
        File directory = new File("src/test/test");
        FileSearcher searcher = new FileSearcher(directory, 0, ".txt");
        boolean found = searcher.compute();
        assertThat(found).isTrue();
    }

    @Test
    public void directoryWithSubdirectoryWithFileSearch() {
        File directory = new File("src/test/test");
        FileSearcher searcher = new FileSearcher(directory, 0, ".test");
        boolean found = searcher.compute();
        assertThat(found).isTrue();
    }

    @Test
    public void directoryWithoutFileSearch() {
        File directory = new File("src/test/test");
        FileSearcher searcher = new FileSearcher(directory, 1, ".test");
        boolean found = searcher.compute();
        assertThat(found).isFalse();
    }
}
