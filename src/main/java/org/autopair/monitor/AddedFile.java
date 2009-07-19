package org.autopair.monitor;

public class AddedFile extends FileSystemChange
{
    public AddedFile(String file)
    {
        super(file);
    }

    @Override
    public String toString()
    {
        return "DeletedFile: " + getFile().getAbsolutePath();
    }
}
