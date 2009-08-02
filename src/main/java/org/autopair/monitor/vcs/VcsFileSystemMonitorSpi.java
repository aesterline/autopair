package org.autopair.monitor.vcs;

import java.util.List;

import com.google.inject.Inject;
import org.autopair.inject.VcsChangeFilter;
import org.autopair.monitor.FileSystemChange;
import org.autopair.monitor.FileSystemChangeFilter;
import org.autopair.monitor.FileSystemMonitorSpi;

public class VcsFileSystemMonitorSpi implements FileSystemMonitorSpi
{
    private Vcs vcs;
    private FileSystemChangeFilter changeFilter;

    @Inject
    public VcsFileSystemMonitorSpi(
            Vcs vcs,
            @VcsChangeFilter FileSystemChangeFilter changeFilter)
    {
        this.vcs = vcs;
        this.changeFilter = changeFilter;
    }

    public List<FileSystemChange> checkForChanges()
    {
        return changeFilter.selectMatching(vcs.status());
    }
}
