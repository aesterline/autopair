package org.autopair.monitor;

import java.io.File;

public class FileSystemChange
{
    private final File file;
    private final SystemChangeType type;

    public FileSystemChange(File file, SystemChangeType type)
    {
        this.file = file;
        this.type = type;
    }

    public FileSystemChange(String file, SystemChangeType type)
    {
        this(new File(file), type);
    }

    public SystemChangeType getType()
    {
        return type;
    }

    public File getFile()
    {
        return file;
    }

    public long lastModifiedTime()
    {
        return file.lastModified();
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        FileSystemChange that = (FileSystemChange) o;

        if(!file.equals(that.file)) return false;
        if(type != that.type) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = file.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
