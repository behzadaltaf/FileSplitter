package com.behzad.splitter.impl;

import com.behzad.splitter.SplitterTestUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by Behzad Altaf
 */
public class RandomSplitterTest {

    private File inputFile = new File("data/UnitTest.in");

    private Double splitValue = 0.8;

    private RandomSplitter randomSplitter;

    @Before
    public void setUp() throws Exception {
        randomSplitter = new RandomSplitter(inputFile, splitValue);
    }

    @Test
    public void splitMethodShouldSplitTheInputFileIntoTwoAndReturnTheHandles() throws Exception {
        Map<String, File> files = randomSplitter.split();
        assertTrue(files.size() == 2);
    }

    @After
    public void tearDown() throws Exception {
        SplitterTestUtil.cleanUpTempFiles(randomSplitter);
    }
}