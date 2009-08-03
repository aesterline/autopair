package org.autopair.java.maven;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Remove
{
    public void all(List<File> files)
    {
        for(File file : files)
        {
            FileUtils.deleteQuietly(file);
        }
    }
}
