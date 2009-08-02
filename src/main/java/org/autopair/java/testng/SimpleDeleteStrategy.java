package org.autopair.java.testng;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.autopair.java.DeleteStrategy;

public class SimpleDeleteStrategy implements DeleteStrategy
{
    public void delete(File file)
    {
        FileUtils.deleteQuietly(file);
    }
}
