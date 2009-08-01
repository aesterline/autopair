package org.autopair.monitor;

import java.util.List;

public interface FileSystemChangeListener
{
    void changes(List<FileSystemChange> changes);
}
