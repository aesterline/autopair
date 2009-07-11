package org.agileide.monitor;

public interface FileSystemMonitorSpi
{
    void setListener(FileSystemChangeListener listener);
    void checkForChanges();
}
