package com.behzad.splitter;

/**
 * Created by Behzad Altaf
 */
public class SplitterTestUtil {

    public static void cleanUpTempFiles(BaseSplitter splitter) {
        if (splitter.trainingSplit.exists())
            splitter.trainingSplit.delete();
        if (splitter.testSplit.exists() )
            splitter.testSplit.delete();
    }
}
