package edu.hw6;

import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.Comparator;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    private static final String TEST_DIR = "testDir";
    private static final String TEST_FILE = "testFile.txt";

    @BeforeAll
    public static void setUp() throws IOException {
        Files.createDirectories(Paths.get(TEST_DIR));
        Files.createFile(Paths.get(TEST_DIR, TEST_FILE));
    }

    @AfterAll
    public static void tearDown() throws IOException {
        Files.walk(Paths.get(TEST_DIR))
            .sorted(Comparator.reverseOrder())
            .map(Path::toFile)
            .forEach(File::delete);
    }

    @Test
    public void fileNameCloneFile() throws IOException {
        Task2.cloneFile(Paths.get(TEST_DIR, TEST_FILE));
        assertThat(Files.exists(Paths.get(TEST_DIR, TEST_FILE.substring(0, 8) + " — копия.txt"))).isTrue();
    }

    @Test
    public void fileNameCloneFileMultipleTimes() throws IOException {
        final Path path = Paths.get(TEST_DIR, TEST_FILE);

        Task2.cloneFile(path);
        for (int i = 1; i <= 4; i++) {
            Task2.cloneFile(path);
            assertThat(Files.exists(Paths.get(TEST_DIR, TEST_FILE.substring(0, 8) + " — копия (" + i + ").txt"))).isTrue();
        }
    }

    @Test
    public void nonExistentFileNameTryCloneFile() {
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            Task2.cloneFile(Paths.get(TEST_DIR, "nonExistentFile.txt"));
        });
    }
}
