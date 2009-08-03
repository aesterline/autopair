package org.autopair.monitor;

public interface FileSystemMonitor
{
    void start(FileSystemChangeListener listener);
}
