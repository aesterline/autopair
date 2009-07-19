package org.autopair.monitor;

public class DeletedFile extends FileSystemChange
{
    public DeletedFile(String file)
    {
        super(file);
    }

    @Override
    public String toString()
    {
        return "DeletedFile: " + getFile().getAbsolutePath();
    }
}
