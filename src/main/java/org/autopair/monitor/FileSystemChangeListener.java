package org.autopair.monitor;

import java.io.File;

public interface FileSystemChangeListener
{
    void newFile(File file);

    void changedFile(File file);

    void deletedFile(File file);
}
