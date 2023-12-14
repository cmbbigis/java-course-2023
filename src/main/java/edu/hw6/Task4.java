package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public final class Task4 {
    private Task4() {
    }

    public static void doTask() {
        String text = "Programming is learned by writing programs. ― Brian Kernighan";
        Path path = Paths.get("output.txt");

        try (OutputStream fileOutputStream = Files.newOutputStream(path);
             CheckedOutputStream checkedOutputStream = new CheckedOutputStream(fileOutputStream, new Adler32());
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(bufferedOutputStream,
                 StandardCharsets.UTF_8);
             PrintWriter printWriter = new PrintWriter(outputStreamWriter)) {

            printWriter.println(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
