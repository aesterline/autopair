package org.autopair;

import java.io.File;

public class CurrentDirectory
{
    private File currentDirectory;

    public CurrentDirectory(File currentDirectory)
    {
        this.currentDirectory = currentDirectory;
    }

    public boolean isDirectoryPresent(String directoryName)
    {
        return new File(currentDirectory, directoryName).exists();
    }
}
