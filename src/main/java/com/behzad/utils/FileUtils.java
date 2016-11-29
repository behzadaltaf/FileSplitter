package com.behzad.utils;

import org.apache.commons.io.FilenameUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Behzad Altaf
 */
public class FileUtils {

    private FileUtils() {
    }

    public static File getFileWithAppendedName(String fileName, String appendedName) {
        File modifiedFile;
        String baseName = FilenameUtils.getBaseName(fileName);
        String path = FilenameUtils.getFullPath(fileName);
        String ext = FilenameUtils.getExtension(fileName);
        if (ext == null || ext.isEmpty()) {
            modifiedFile = new File(path + baseName + "_" + appendedName + ext);
        } else {
            modifiedFile = new File(path + baseName + "_" + appendedName + "." + ext);
        }
        return modifiedFile;
    }

    public static long getTotalLinesInFile(File file) throws IOException {
        return Files.lines(Paths.get(file.getAbsolutePath())).count();
    }

    public static BufferedReader getBufferedReader(File file) throws IOException {
            return Files.newBufferedReader(Paths.get(file.getPath()));
    }

    public static BufferedWriter getBufferedWriter(File file) throws IOException {
        return Files.newBufferedWriter(Paths.get(file.getPath()));
    }
}
