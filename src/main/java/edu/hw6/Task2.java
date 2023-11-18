package edu.hw6;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class Task2 {
    private Task2() {
    }

    private static final String COPY_STRING = " — копия";

    public static void cloneFile(Path path) throws IOException {
        if (Files.exists(path)) {
            String fileName = path.getFileName().toString();
            String baseName = fileName.contains(".") ? fileName.substring(0, fileName.lastIndexOf(".")) : fileName;
            String extension = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".")) : "";
            Path directory = path.getParent();
            int copyNumber = 1;

            if (Files.exists(Paths.get(path.toString().substring(0, path.toString().length() - extension.length())
                + COPY_STRING + extension))) {
                copyNumber++;
            }

            while (true) {
                String newFileName = baseName + COPY_STRING + (copyNumber > 1 ? " (" + (copyNumber - 1) + ")" : "")
                    + extension;
                Path newPath = directory.resolve(newFileName);

                if (!Files.exists(newPath)) {
                    Files.copy(path, newPath);
                    break;
                }

                copyNumber++;
            }
        } else {
            throw new FileNotFoundException("File not found: " + path);
        }
    }
}
