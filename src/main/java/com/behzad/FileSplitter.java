package com.behzad;

import com.behzad.constants.Constants;
import com.behzad.enums.InputSplitStrategy;
import com.behzad.factory.impl.SplitterFactoryImpl;
import org.apache.commons.lang3.EnumUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Behzad Altaf
 */
public class FileSplitter {

    public static Map<String, File> splitFile(File inputFile, String splitStrategy, Double splitRatio) throws IOException {
        validateInput(splitStrategy, splitRatio);
        return SplitterFactoryImpl.getInstance().getSplitter(InputSplitStrategy.valueOf(splitStrategy.toUpperCase()), inputFile, splitRatio).split();
    }

    static void validateInput(String splitStrategy, Double splitRatio) {
        if (!EnumUtils.isValidEnum(InputSplitStrategy.class, splitStrategy.toUpperCase())) {
            throw new IllegalArgumentException(Constants.INVALID_SPLIT_STRATEGY);
        }

        if (splitRatio <= 0.0 || splitRatio > 1.0) {
            throw new IllegalArgumentException(Constants.INVALID_TRAINING_SPLIT);
        }
    }
}
