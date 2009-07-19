package org.autopair.monitor;

import java.io.File;

public class ChangedFile extends FileSystemChange
{
    public ChangedFile(AddedFile added)
    {
        this(added.getFile());
    }

    public ChangedFile(File file)
    {
        super(file);
    }

    public ChangedFile(String file)
    {
        super(file);
    }

    @Override
    public String toString()
    {
        return "ChangedFile: " + getFile().getAbsolutePath();
    }
}
