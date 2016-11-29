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
public class LinearReverseSplitterTest {

    private File inputFile = new File("data/UnitTest.in");

    private Double splitValue = 0.8;

    private LinearReverseSplitter linearReverseSplitter;

    @Before
    public void setUp() throws Exception {
        linearReverseSplitter = new LinearReverseSplitter(inputFile, splitValue);
    }

    @Test
    public void splitMethodShouldSplitTheInputFileIntoTwoAndReturnTheHandles() throws Exception {
        Map<String, File> files = linearReverseSplitter.split();
        assertTrue(files.size() == 2);
    }

    @After
    public void tearDown() throws Exception {
        SplitterTestUtil.cleanUpTempFiles(linearReverseSplitter);
    }
}