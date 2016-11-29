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
public class StratifiedSplitterTest {

    private File inputFile = new File("data/UnitTest.in");

    private StratifiedSplitter stratifiedSplitter;

    @Before
    public void setUp() throws Exception {
        Double splitValue = 0.5;
        stratifiedSplitter = new StratifiedSplitter(inputFile, splitValue);
    }

    @Test
    public void splitMethodShouldSplitTheInputFilesAndStratifyCategories() throws Exception {
        Map<String, File> files = stratifiedSplitter.split();
        assertTrue(files.size() == 2);
        stratifiedSplitter.trainingCategories.forEach((x, y) ->
                assertTrue(y / stratifiedSplitter.testCategories.get(x) >= stratifiedSplitter.pivotRatio));
    }

    @After
    public void tearDown() throws Exception {
        SplitterTestUtil.cleanUpTempFiles(stratifiedSplitter);
    }
}