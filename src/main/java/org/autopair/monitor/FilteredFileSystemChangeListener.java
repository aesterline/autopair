package org.autopair.monitor;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public class FilteredFileSystemChangeListener implements FileSystemChangeListener
{
    private FileSystemChangeFilter filter;
    private FileSystemChangeListener delegatee;

    public FilteredFileSystemChangeListener(FileSystemChangeFilter filter, FileSystemChangeListener delegatee)
    {
        this.filter = filter;
        this.delegatee = delegatee;
    }

    public void changes(List<FileSystemChange> changes)
    {
        List<FileSystemChange> filteredChanges = filter.selectMatching(changes);
        if(CollectionUtils.isNotEmpty(filteredChanges))
        {
            delegatee.changes(filteredChanges);
        }
    }
}
