package org.autopair.monitor.vcs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.autopair.monitor.AddedFile;
import org.autopair.monitor.ChangedFile;
import org.autopair.monitor.FileSystemChange;
import org.autopair.monitor.FileSystemChangeFilter;

public class AddedChangesFileSystemChangeFilter implements FileSystemChangeFilter
{
    private Set<AddedFile> addedFiles = new HashSet<AddedFile>();

    public List<FileSystemChange> selectMatching(List<FileSystemChange> changes)
    {
        List<FileSystemChange> selected = new ArrayList<FileSystemChange>(changes);
        final Set<AddedFile> currentAddedFiles = new HashSet<AddedFile>();

        CollectionUtils.transform(selected, new Transformer()
        {
            public Object transform(Object input)
            {
                FileSystemChange change = (FileSystemChange) input;
                if(change instanceof AddedFile)
                {
                    AddedFile added = (AddedFile) change;
                    currentAddedFiles.add(added);
                    if(addedFiles.contains(added))
                    {
                        return new ChangedFile(added);
                    }
                    else
                    {
                        addedFiles.add(added);
                    }
                }

                return change;
            }
        });

        addedFiles.retainAll(currentAddedFiles);

        return selected;
    }
}
