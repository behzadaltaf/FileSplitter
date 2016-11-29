package com.behzad.factory.impl;

import com.behzad.enums.InputSplitStrategy;
import com.behzad.factory.SplitterFactory;
import com.behzad.splitter.Splitter;
import com.behzad.splitter.impl.LinearReverseSplitter;
import com.behzad.splitter.impl.LinearSplitter;
import com.behzad.splitter.impl.RandomSplitter;
import com.behzad.splitter.impl.StratifiedSplitter;

import java.io.File;
import java.io.IOException;

/**
 * Created by Behzad Altaf
 */
public class SplitterFactoryImpl implements SplitterFactory {


    private static SplitterFactoryImpl splitterFactory;

    private SplitterFactoryImpl() {

    }

    public static SplitterFactoryImpl getInstance() {
        if (splitterFactory == null) {
            splitterFactory = new SplitterFactoryImpl();
        }
        return splitterFactory;
    }

    @Override
    public Splitter getSplitter(InputSplitStrategy splitStrategy, File inputFile, Double splitValue) throws IOException {
        switch (splitStrategy) {
            case LINEARREVERSE:
                return new LinearReverseSplitter(inputFile, splitValue);
            case RANDOM:
                return new RandomSplitter(inputFile, splitValue);
            case STRATIFIED:
                return new StratifiedSplitter(inputFile, splitValue);
            case LINEAR:
            default:
                return new LinearSplitter(inputFile, splitValue);
        }
    }
}
