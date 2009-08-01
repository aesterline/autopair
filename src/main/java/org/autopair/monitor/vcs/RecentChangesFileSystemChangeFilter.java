package org.autopair.monitor.vcs;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.autopair.monitor.FileSystemChange;
import org.autopair.monitor.FileSystemChangeFilter;

public class RecentChangesFileSystemChangeFilter implements FileSystemChangeFilter
{
    private Clock clock;

    public RecentChangesFileSystemChangeFilter(Clock clock)
    {
        this.clock = clock;
    }

    public List<FileSystemChange> selectMatching(List<FileSystemChange> changes)
    {
        List<FileSystemChange> selected = new ArrayList<FileSystemChange>();

        CollectionUtils.select(
                changes,
                new Predicate()
                {
                    public boolean evaluate(Object object)
                    {
                        FileSystemChange change = (FileSystemChange) object;
                        return change.lastModifiedTime() > clock.currentMark();
                    }
                },
                selected);
        clock.mark();

        return selected;
    }
}
