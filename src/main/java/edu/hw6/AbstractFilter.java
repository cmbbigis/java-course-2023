package edu.hw6;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    boolean accept(Path entry);

    default AbstractFilter and(AbstractFilter other) {
        return entry -> this.accept(entry) && other.accept(entry);
    }

    static AbstractFilter regularFile() {
        return Files::isRegularFile;
    }

    static AbstractFilter readable() {
        return Files::isReadable;
    }

    static AbstractFilter writable() {
        return Files::isWritable;
    }

    static AbstractFilter largerThan(long size) {
        return path -> {
            try {
                return Files.size(path) > size;
            } catch (IOException e) {
                return false;
            }
        };
    }

    static AbstractFilter extension(String ext) {
        return path -> path.toString().endsWith(ext);
    }

    static AbstractFilter regexMatches(String regex) {
        Pattern pattern = Pattern.compile(regex);
        return path -> pattern.matcher(path.getFileName().toString()).matches();
    }

    static AbstractFilter magicNumber(int... numbers) {
        return path -> {
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
        };
    }

    static AbstractFilter globMatches(String glob) {
        FileSystem fs = FileSystems.getDefault();
        PathMatcher matcher = fs.getPathMatcher("glob:**" + glob);
        return matcher::matches;
    }

    static AbstractFilter regexContains(String regex) {
        Pattern pattern = Pattern.compile(regex);
        return path -> pattern.matcher(path.getFileName().toString()).find();
    }
}
