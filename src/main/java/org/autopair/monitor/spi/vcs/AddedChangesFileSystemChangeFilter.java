package org.autopair.monitor.spi.vcs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.autopair.monitor.FileSystemChange;
import org.autopair.monitor.FileSystemChangeFilter;
import org.autopair.monitor.SystemChangeType;

public class AddedChangesFileSystemChangeFilter implements FileSystemChangeFilter
{
    private Set<FileSystemChange> addedFiles = new HashSet<FileSystemChange>();

    public List<FileSystemChange> selectMatching(List<FileSystemChange> changes)
    {
        List<FileSystemChange> selected = new ArrayList<FileSystemChange>(changes);
        final Set<FileSystemChange> currentAddedFiles = new HashSet<FileSystemChange>();

        CollectionUtils.transform(selected, new Transformer()
        {
            public Object transform(Object input)
            {
                FileSystemChange change = (FileSystemChange) input;
                if(change.getType() == SystemChangeType.ADDED)
                {
                    currentAddedFiles.add(change);
                    if(addedFiles.contains(change))
                    {
                        return new FileSystemChange(change.getFile(), SystemChangeType.MODIFIED);
                    }
                    else
                    {
                        addedFiles.add(change);
                    }
                }

                return change;
            }
        });

        addedFiles.retainAll(currentAddedFiles);

        return selected;
    }
}
