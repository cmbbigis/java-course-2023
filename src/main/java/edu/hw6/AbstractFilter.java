package edu.hw6;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public abstract class AbstractFilter implements DirectoryStream.Filter<Path> {
    private final Predicate<Path> predicate;

    public AbstractFilter(Predicate<Path> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean accept(Path entry) {
        return predicate.test(entry);
    }

    public AbstractFilter and(AbstractFilter other) {
        return new AbstractFilter(this.predicate.and(other.predicate)) {};
    }

    public static final AbstractFilter REGULAR_FILE = new AbstractFilter(Files::isRegularFile) {};
    public static final AbstractFilter READABLE = new AbstractFilter(Files::isReadable) {};
    public static final AbstractFilter WRITABLE = new AbstractFilter(Files::isWritable) {};

    public static AbstractFilter largerThan(long size) {
        return new AbstractFilter(path -> {
            try {
                return Files.size(path) > size;
            } catch (IOException e) {
                return false;
            }
        }) {};
    }

    public static AbstractFilter extension(String ext) {
        return new AbstractFilter(path -> path.toString().endsWith(ext)) {};
    }

    public static AbstractFilter regexMatches(String regex) {
        Pattern pattern = Pattern.compile(regex);
        return new AbstractFilter(path -> pattern.matcher(path.getFileName().toString()).matches()) {};
    }

    public static AbstractFilter magicNumber(int... numbers) {
        return new AbstractFilter(path -> {
            try {
                byte[] bytes = Files.readAllBytes(path);
                if (bytes.length < numbers.length) {
                    return false;
                }
                for (int i = 0; i < bytes.length; i++) {
                    int finalI = i;
                    if (IntStream.of(numbers).anyMatch(x -> x == bytes[finalI])) {
                        return true;
                    }
                }
                return false;
            } catch (IOException e) {
                return false;
            }
        }) {};
    }

    public static AbstractFilter globMatches(String glob) {
        FileSystem fs = FileSystems.getDefault();
        PathMatcher matcher = fs.getPathMatcher("glob:**" + glob);
        return new AbstractFilter(matcher::matches) {};
    }

    public static AbstractFilter regexContains(String regex) {
        Pattern pattern = Pattern.compile(regex);
        return new AbstractFilter(path -> pattern.matcher(path.getFileName().toString()).find()) {};
    }
}
