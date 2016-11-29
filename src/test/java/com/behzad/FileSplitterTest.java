package com.behzad;

import com.behzad.constants.Constants;
import com.behzad.splitter.SplitterTestUtil;
import com.behzad.splitter.impl.LinearReverseSplitter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Behzad Altaf
 */
public class FileSplitterTest {

    private File inputFile = new File("data/UnitTest.in");

    private Double splitValue = 0.8;

    private static Map<String, File> splitHandles;

    @Test
    public void splitFileShouldReturnAMapWithSplitHandles() throws Exception {
        splitHandles = FileSplitter.splitFile(inputFile, "linear", splitValue);
        assertNotNull(splitHandles.get(Constants.TEST));
        assertNotNull(splitHandles.get(Constants.TRAINING));
        assertNotNull(splitHandles);
    }

    @Test(expected=IllegalArgumentException.class)
    public void invalidSplitStrategyShouldThrowIllegalArgumentException() throws Exception {
        FileSplitter.validateInput("Something", splitValue);
    }

    @Test(expected=IllegalArgumentException.class)
    public void invalidSplitValueShouldThrowIllegalArgumentException() throws Exception {
        FileSplitter.validateInput("linear", 1.1);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        splitHandles.get(Constants.TEST).delete();
        splitHandles.get(Constants.TRAINING).delete();
    }
}