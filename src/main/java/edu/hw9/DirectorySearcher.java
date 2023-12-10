package edu.hw9;

import java.io.File;
import java.util.concurrent.RecursiveTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DirectorySearcher extends RecursiveTask<Integer> {
    private final File directory;
    private final static Logger LOGGER = LogManager.getLogger();
    private final static int THOUSAND = 1000;

    public DirectorySearcher(File directory) {
        this.directory = directory;
    }

    @Override
    protected Integer compute() {
        int count = 0;
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    count++;
                } else if (file.isDirectory()) {
                    DirectorySearcher directorySearcher = new DirectorySearcher(file);
                    directorySearcher.fork();
                    count += directorySearcher.join();
                }
            }
        }

        if (count > THOUSAND) {
            LOGGER.trace(directory.getPath() + " contains " + count + " files");
        }

        return count;
    }
}
