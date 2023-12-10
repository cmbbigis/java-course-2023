package edu.hw9;

import java.io.File;
import java.util.concurrent.RecursiveTask;

public class FileSearcher extends RecursiveTask<Boolean> {
    private final File file;
    private final long size;
    private final String extension;

    public FileSearcher(File file, long size, String extension) {
        this.file = file;
        this.size = size;
        this.extension = extension;
    }

    @Override
    protected Boolean compute() {
        File[] files = file.listFiles();

        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    FileSearcher searcher = new FileSearcher(f, size, extension);
                    searcher.fork();

                    if (searcher.join()) {
                        return true;
                    }
                } else {
                    if (f.length() >= size && f.getName().endsWith(extension)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
