package com.behzad.splitter.impl;

import com.behzad.splitter.BaseSplitter;
import com.behzad.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Behzad Altaf
 */
public class LinearSplitter extends BaseSplitter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinearSplitter.class);

    public LinearSplitter(File inputFileForModelGeneration, Double trainingSplitValue) throws IOException {
        super(inputFileForModelGeneration, trainingSplitValue);
    }

    @Override
    public Map<String, File> split() throws IOException {
        try (BufferedReader reader = FileUtils.getBufferedReader(inputFileForModelGeneration);
             BufferedWriter trainingWriter = FileUtils.getBufferedWriter(trainingSplit);
             BufferedWriter testWriter = FileUtils.getBufferedWriter(testSplit))  {
            for (long i = 0; i < trainingCount; i++) {
                trainingWriter.write(reader.readLine());
                trainingWriter.newLine();
            }
            String line;
            while ((line = reader.readLine()) != null) {
                testWriter.write(line);
                testWriter.newLine();
            }
        } catch (IOException e) {
            LOGGER.error("Error splitting input file.", e);
        }
        return splitFiles;
    }
}
