package edu.hw9;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DirectorySearcherTest {
    @Test
    public void directoryWithMoreThan1000FilesCompute() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        File directory = new File("src/test/test");
        DirectorySearcher counter = new DirectorySearcher(directory);
        counter.compute();

        String output = outContent.toString();
        assertThat(output.contains("src/test/test")).isTrue();
    }

    @Test
    public void directoryWithLessThan1000FilesCompute() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        File directory = new File("src/test/test1");
        DirectorySearcher counter = new DirectorySearcher(directory);
        counter.compute();

        String output = outContent.toString();
        assertThat(output.contains("src/test/test1")).isFalse();
    }
}
