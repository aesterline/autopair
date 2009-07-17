package org.autopair.monitor;

import java.io.File;
import java.util.List;

import org.apache.commons.logging.Log;

public class LoggingFileSystemChangeListener implements FileSystemChangeListener
{
    private Log log;

    public LoggingFileSystemChangeListener(Log log)
    {
        this.log = log;
    }

    public void newFile(File file)
    {
        log.info(String.format("\tnew: %s", file.getName()));
    }

    public void changedFile(File file)
    {
        log.info(String.format("\tchanged: %s", file.getName()));
    }

    public void deletedFile(File file)
    {
        log.info(String.format("\tdeleted: %s", file.getName()));
    }

    public void changes(List<FileSystemChange> changes)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
