package org.agileide.monitor;

import java.io.File;

public interface FileSystemChangeListener
{
    void newFile(File file);
    void changedFile(File file);
}
