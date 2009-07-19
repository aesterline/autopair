package org.autopair.monitor;

import java.io.File;

public class DeletedFile implements FileSystemChange
{
    private File file;

    public DeletedFile(File file)
    {
        this.file = file;
    }

    public long lastUpdateTime()
    {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        DeletedFile that = (DeletedFile) o;

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
        return "DeletedFile: " + file.getAbsolutePath();
    }
}
