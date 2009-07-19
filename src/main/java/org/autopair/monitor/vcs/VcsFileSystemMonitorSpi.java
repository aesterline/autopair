package org.autopair.monitor.vcs;

import java.util.List;

import org.autopair.monitor.FileSystemChange;
import org.autopair.monitor.FileSystemChangeFilter;
import org.autopair.monitor.FileSystemChangeListener;
import org.autopair.monitor.FileSystemMonitorSpi;

public class VcsFileSystemMonitorSpi implements FileSystemMonitorSpi
{
    private Vcs vcs;
    private FileSystemChangeFilter changeFilter;

    public VcsFileSystemMonitorSpi(Vcs vcs, FileSystemChangeFilter changeFilter)
    {
        this.vcs = vcs;
        this.changeFilter = changeFilter;
    }

    public List<FileSystemChange> checkForChanges()
    {
        return changeFilter.selectMatching(vcs.status());
    }

    public void setListener(FileSystemChangeListener listener)
    {
    }
}
