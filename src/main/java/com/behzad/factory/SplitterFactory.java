package com.behzad.factory;


import com.behzad.enums.InputSplitStrategy;
import com.behzad.splitter.Splitter;

import java.io.File;
import java.io.IOException;

/**
 * Created by Behzad Altaf
 */
public interface SplitterFactory {

    Splitter getSplitter(InputSplitStrategy splitStrategy, File inputFile, Double splitValue) throws IOException;
}
