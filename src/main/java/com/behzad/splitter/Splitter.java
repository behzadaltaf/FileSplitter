package com.behzad.splitter;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Behzad Altaf
 */
public interface Splitter {

    Map<String, File> split() throws IOException;

}
