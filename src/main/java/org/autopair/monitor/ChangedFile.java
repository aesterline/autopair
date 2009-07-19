package org.autopair.monitor;

import java.io.File;

public class ChangedFile implements FileSystemChange
{
    private File changedFile;

    public ChangedFile(AddedFile added) {}

    public ChangedFile(File changedFile)
    {
        this.changedFile = changedFile;
    }

    public long lastUpdateTime()
    {
        return changedFile.lastModified();
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        ChangedFile that = (ChangedFile) o;

        if(changedFile != null ? !changedFile.equals(that.changedFile) : that.changedFile != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return changedFile != null ? changedFile.hashCode() : 0;
    }
}
