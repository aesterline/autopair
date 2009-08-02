package org.autopair.java;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class SimpleDeleteStrategy implements DeleteStrategy
{
    public void delete(File file)
    {
        FileUtils.deleteQuietly(file);
    }
}
