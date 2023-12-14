package edu.hw6;

import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.Comparator;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class AbstractFilterTest {
    private static final String TEST_DIR = "testDir";
    private static final String TEST_FILE = "testFile.txt";

    @BeforeAll
    public static void setUp() throws IOException {
        Files.createDirectories(Paths.get(TEST_DIR));
        Files.write(Paths.get(TEST_DIR, TEST_FILE), "some data".getBytes());
        Files.write(Paths.get(TEST_DIR, "testFileWithMagicNumbers.txt"), "test123test".getBytes());
    }

    @AfterAll
    public static void tearDown() throws IOException {
        Files.walk(Paths.get(TEST_DIR))
            .sorted(Comparator.reverseOrder())
            .map(Path::toFile)
            .forEach(File::delete);
    }

    @Test
    public void regularFile() {
        AbstractFilter filter = AbstractFilter.regularFile();
        assertThat(filter.accept(Paths.get(TEST_DIR, TEST_FILE))).isTrue();
    }

    @Test
    public void readable() {
        AbstractFilter filter = AbstractFilter.readable();
        assertThat(filter.accept(Paths.get(TEST_DIR, TEST_FILE))).isTrue();
    }

    @Test
    public void writable() {
        AbstractFilter filter = AbstractFilter.writable();
        assertThat(filter.accept(Paths.get(TEST_DIR, TEST_FILE))).isTrue();
    }

    @Test
    public void largerThan() {
        AbstractFilter filter = AbstractFilter.largerThan(0);
        assertThat(filter.accept(Paths.get(TEST_DIR, TEST_FILE))).isTrue();
    }

    @Test
    public void extension() {
        AbstractFilter filter = AbstractFilter.extension(".txt");
        assertThat(filter.accept(Paths.get(TEST_DIR, TEST_FILE))).isTrue();
    }

    @Test
    public void regexMatches() {
        AbstractFilter filter = AbstractFilter.regexMatches(".*\\.txt");
        assertThat(filter.accept(Paths.get(TEST_DIR, TEST_FILE))).isTrue();
    }

    @Test
    public void magicNumber() {
        AbstractFilter filter = AbstractFilter.magicNumber('1', '2', '3');
        assertThat(filter.accept(Paths.get(TEST_DIR, "testFileWithMagicNumbers.txt"))).isTrue();
    }

    @Test
    public void globMatches() {
        AbstractFilter filter = AbstractFilter.globMatches("*.txt");
        assertThat(filter.accept(Paths.get(TEST_DIR, TEST_FILE))).isTrue();
    }

    @Test
    public void regexContains() {
        AbstractFilter filter = AbstractFilter.regexContains("File");
        assertThat(filter.accept(Paths.get(TEST_DIR, TEST_FILE))).isTrue();
    }

    @Test
    public void and() {
        AbstractFilter filter = AbstractFilter.regexContains("File").and(AbstractFilter.extension(".txt"));
        assertThat(filter.accept(Paths.get(TEST_DIR, TEST_FILE))).isTrue();
    }
}
