package org.autopair.monitor;

import java.util.List;

public class FileSystemChangeFilterChain implements FileSystemChangeFilter
{
    private FileSystemChangeFilter[] chain;

    public FileSystemChangeFilterChain(FileSystemChangeFilter... chain)
    {
        this.chain = chain;
    }

    public List<FileSystemChange> selectMatching(List<FileSystemChange> changes)
    {
        List<FileSystemChange> selection = changes;

        for(FileSystemChangeFilter filter : chain)
        {
            selection = filter.selectMatching(selection);
        }

        return selection;
    }
}
