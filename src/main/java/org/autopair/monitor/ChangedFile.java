package org.autopair.monitor;

public class ChangedFile implements FileSystemChange
{
    private long whocares = 0;

    public ChangedFile(AddedFile added) {}

    public long lastUpdateTime()
    {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        ChangedFile that = (ChangedFile) o;

        if(whocares != that.whocares) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return (int) (whocares ^ (whocares >>> 32));
    }
}
