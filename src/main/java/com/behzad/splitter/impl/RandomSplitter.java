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
import java.util.Random;

/**
 * Created by Behzad Altaf
 */
public class RandomSplitter extends BaseSplitter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RandomSplitter.class);

    public RandomSplitter(File inputFileForModelGeneration, Double trainingSplitValue) throws IOException {
        super(inputFileForModelGeneration, trainingSplitValue);
    }

    @Override
    public Map<String, File> split() throws IOException {
        LOGGER.debug("Start Count, TRAINING: " + trainingCount + " TEST: " + testCount);
        try (BufferedReader reader = FileUtils.getBufferedReader(inputFileForModelGeneration);
             BufferedWriter trainingWriter = FileUtils.getBufferedWriter(trainingSplit);
             BufferedWriter testWriter = FileUtils.getBufferedWriter(testSplit)) {
            BufferedWriter[] writers = {trainingWriter, testWriter};
            Random random = new Random();
            String line;
            long[] totalLinesInFiles = {0L, 0L};
            while ((line = reader.readLine()) != null) {
                if (testCount >= totalLinesInFiles[1]) {
                    int randomIndex = random.nextBoolean() ? 0 : 1;
                    writers[randomIndex].write(line);
                    writers[randomIndex].newLine();
                    totalLinesInFiles[randomIndex]++;
                } else {
                    trainingWriter.write(line);
                    trainingWriter.newLine();
                }
            }
            LOGGER.debug("Final Count, TRAINING: " + totalLinesInFiles[0] + " TEST: " + totalLinesInFiles[1]);
        } catch (IOException e) {
            LOGGER.error("Error splitting input file.", e);
        }
        return splitFiles;
    }
}
