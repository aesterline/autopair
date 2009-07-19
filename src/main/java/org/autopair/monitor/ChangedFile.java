package org.autopair.monitor;

import java.io.File;

public class ChangedFile implements FileSystemChange
{
    private File file;

    public ChangedFile(AddedFile added)
    {
        this(added.getFile());
    }

    public ChangedFile(File file)
    {
        this.file = file;
    }

    public long lastUpdateTime()
    {
        return file.lastModified();
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        ChangedFile that = (ChangedFile) o;

        if(file != null ? !file.equals(that.file) : that.file != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return file != null ? file.hashCode() : 0;
    }

    @Override
    public String toString()
    {
        return "ChangedFile: " + file.getAbsolutePath();
    }
}
