package org.agileide.monitor;

public interface FileSystemMonitorSpi extends FileSystemMonitor
{
    void checkForChanges();
}
