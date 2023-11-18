package edu.hw6;

import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class Task4Test {
    private static final String TEST_FILE = "output.txt";
    private static final String EXPECTED_TEXT = "Programming is learned by writing programs. ― Brian Kernighan";

    @AfterAll
    public static void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_FILE));
    }

    @Test
    public void testTask() throws IOException {
        Task4.doTask();

        String content = Files.readString(Paths.get(TEST_FILE));
        assertThat(content).isEqualTo(EXPECTED_TEXT + System.lineSeparator());
    }
}
