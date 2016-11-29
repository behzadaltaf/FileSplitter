package com.behzad.splitter.impl;


import com.behzad.splitter.BaseSplitter;
import com.behzad.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Behzad Altaf
 */
public class StratifiedSplitter extends BaseSplitter {

    private static final Logger LOGGER = LoggerFactory.getLogger(StratifiedSplitter.class);

    private final int TRAINING = 0;

    private final int TEST = 1;

    Map<String, Long> trainingCategories;

    Map<String, Long> testCategories;

    double pivotRatio;

    public StratifiedSplitter(File inputFileForModelGeneration, Double trainingSplitValue) throws IOException {
        super(inputFileForModelGeneration, trainingSplitValue);
        trainingCategories = new HashMap<>();
        testCategories = new HashMap<>();
        pivotRatio = trainingSplitValue / (1 - trainingSplitValue);
    }

    @Override
    public Map<String, File> split() throws IOException {
        try (BufferedReader reader = FileUtils.getBufferedReader(inputFileForModelGeneration);
             BufferedWriter trainingWriter = FileUtils.getBufferedWriter(trainingSplit);
             BufferedWriter testWriter = FileUtils.getBufferedWriter(testSplit)) {
            BufferedWriter[] writers = {trainingWriter, testWriter};
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(" ", 2);
                BufferedWriter writer = getWriter(writers, split[0]);
                writer.write(line);
                writer.newLine();
            }
            LOGGER.debug("Split in training: {}", trainingCategories);
            LOGGER.debug("Split in test: {}", testCategories);
        } catch (IOException e) {
            LOGGER.error("Error splitting input file.", e);
        }
        return splitFiles;
    }

    private BufferedWriter getWriter(BufferedWriter[] writers, String category) {
        Long trainingTotal = trainingCategories.get(category);
        Long testTotal = testCategories.get(category);
        BufferedWriter writer;
        if (trainingTotal == null) {
            trainingCategories.put(category, 1L);
            writer = writers[TRAINING];
        } else if (testTotal == null) {
            testCategories.put(category, 1L);
            writer = writers[TEST];
        } else if (trainingTotal / (double) testTotal > pivotRatio) {
            testCategories.put(category, ++testTotal);
            writer = writers[TEST];
        } else {
            trainingCategories.put(category, ++trainingTotal);
            writer = writers[TRAINING];
        }
        return writer;
    }
}