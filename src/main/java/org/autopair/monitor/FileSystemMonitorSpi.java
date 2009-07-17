package org.autopair.monitor;

import java.util.List;

public interface FileSystemMonitorSpi extends FileSystemMonitor
{
    List<FileSystemChange> checkForChanges();
}
