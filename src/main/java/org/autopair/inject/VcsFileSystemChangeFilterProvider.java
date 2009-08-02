package org.autopair.inject;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.autopair.monitor.FileSystemChangeFilter;
import org.autopair.monitor.FileSystemChangeFilterChain;
import org.autopair.monitor.vcs.AddedChangesFileSystemChangeFilter;
import org.autopair.monitor.vcs.RecentChangesFileSystemChangeFilter;

public class VcsFileSystemChangeFilterProvider implements Provider<FileSystemChangeFilter>
{
    private RecentChangesFileSystemChangeFilter recentChanges;
    private AddedChangesFileSystemChangeFilter addedChanges;

    @Inject
    public VcsFileSystemChangeFilterProvider(
            RecentChangesFileSystemChangeFilter recentChanges,
            AddedChangesFileSystemChangeFilter addedChanges)
    {
        this.recentChanges = recentChanges;
        this.addedChanges = addedChanges;
    }

    public FileSystemChangeFilter get()
    {
        return new FileSystemChangeFilterChain(recentChanges, addedChanges);
    }
}
