package org.autopair.monitor;

import java.io.File;

public class AddedFile implements FileSystemChange
{
    private final File file;

    public AddedFile(File file)
    {
        this.file = file;
    }

    public long lastUpdateTime()
    {
        return file.lastModified();
    }

    public File getFile()
    {
        return file;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        AddedFile addedFile = (AddedFile) o;

        if(!file.equals(addedFile.file)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return file.hashCode();
    }

    @Override
    public String toString()
    {
        return "DeletedFile: " + file.getAbsolutePath();
    }
}
