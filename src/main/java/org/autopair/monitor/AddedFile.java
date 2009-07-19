package org.autopair.monitor;

import java.io.File;

public class AddedFile implements FileSystemChange
{
    private final File newFile;

    public AddedFile(File newFile)
    {
        this.newFile = newFile;
    }

    public long lastUpdateTime()
    {
        return newFile.lastModified();
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        AddedFile addedFile = (AddedFile) o;

        if(!newFile.equals(addedFile.newFile)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return newFile.hashCode();
    }
}
