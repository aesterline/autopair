package org.autopair.monitor;

import java.util.List;

public interface FileSystemMonitorSpi
{
    List<FileSystemChange> checkForChanges();
}
