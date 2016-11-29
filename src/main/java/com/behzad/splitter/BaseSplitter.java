package com.behzad.splitter;

import com.behzad.constants.Constants;
import com.behzad.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Behzad Altaf
 */
public abstract class BaseSplitter implements Splitter {

    protected Long trainingCount;

    protected Long testCount;

    protected File trainingSplit;

    protected File testSplit;

    protected Map<String, File> splitFiles;

    protected File inputFileForModelGeneration;

    public BaseSplitter(File inputFileForModelGeneration, Double trainingSplitValue) throws IOException {

        String randomString = UUID.randomUUID().toString();

        this.inputFileForModelGeneration = inputFileForModelGeneration;

        trainingSplit = FileUtils.getFileWithAppendedName(inputFileForModelGeneration.getAbsolutePath(), randomString + Constants.TRAINING);

        testSplit = FileUtils.getFileWithAppendedName(inputFileForModelGeneration.getAbsolutePath(), randomString + Constants.TEST);

        splitFiles = new HashMap<>(2);

        splitFiles.put(Constants.TRAINING, trainingSplit);

        splitFiles.put(Constants.TEST, testSplit);

        Long totalNumberOfLines = FileUtils.getTotalLinesInFile(inputFileForModelGeneration);

        trainingCount = (long) (totalNumberOfLines * trainingSplitValue);

        testCount = totalNumberOfLines - trainingCount;
    }
}
