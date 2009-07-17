package org.autopair.monitor;

import java.io.File;
import java.util.List;

public interface FileSystemChangeListener
{
    void newFile(File file);

    void changedFile(File file);

    void deletedFile(File file);

    void changes(List<FileSystemChange> changes);
}
