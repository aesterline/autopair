package org.autopair.monitor;

import java.io.File;

public abstract class FileSystemChange
{
    private final File file;

    public FileSystemChange(File file)
    {
        this.file = file;
    }

    public FileSystemChange(String file)
    {
        this(new File(file));
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

        FileSystemChange that = (FileSystemChange) o;

        if(file != null ? !file.equals(that.file) : that.file != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return file != null ? file.hashCode() : 0;
    }
}
