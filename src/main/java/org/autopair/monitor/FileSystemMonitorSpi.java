package org.autopair.monitor;

public interface FileSystemMonitorSpi extends FileSystemMonitor
{
    void checkForChanges();
}
