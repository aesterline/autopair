package org.autopair.java;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class DeleteStrategy
{
    public void delete(File file)
    {
        FileUtils.deleteQuietly(file);
    }
}
