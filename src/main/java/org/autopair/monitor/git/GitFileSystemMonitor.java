package org.autopair.monitor.git;

import java.io.File;

import org.autopair.monitor.FileSystemChangeListener;
import org.autopair.monitor.FileSystemMonitorSpi;

public class GitFileSystemMonitor implements FileSystemMonitorSpi
{
    private GitStatus gitStatus;
    private FileSystemChangeListener listener;

    private static final String MODIFIED = "#\tmodified:";
    private static final String DELETED = "#\tdeleted:";
    private static final String NEW = "#\tnew file:";
    private static final String RENAMED = "#\trenamed:";

    public GitFileSystemMonitor(GitStatus gitStatus)
    {
        this.gitStatus = gitStatus;
    }

    public void setListener(FileSystemChangeListener listener)
    {
        this.listener = listener;
    }

    public void checkForChanges()
    {
        String[] changes = gitStatus.status().split("\n");
        boolean newFiles = false;

        for(String change : changes)
        {
            if(change.startsWith(MODIFIED))
            {
                String modifiedFile = change.substring(MODIFIED.length()).trim();
                listener.changedFile(new File(modifiedFile));
            }
            else if(change.startsWith(DELETED))
            {
                String deletedFile = change.substring(DELETED.length()).trim();
                listener.deletedFile(new File(deletedFile));
            }
            else if(change.startsWith(NEW))
            {
                String newFile = change.substring(NEW.length()).trim();
                listener.newFile(new File(newFile));
            }
            else if(change.startsWith(RENAMED))
            {
                String renameDefinition = change.substring(RENAMED.length()).trim();
                String[] renameParts = renameDefinition.split("->");

                listener.deletedFile(new File(renameParts[0].trim()));
                listener.newFile(new File(renameParts[1].trim()));
            }
            else if(change.contains("Untracked files"))
            {
                newFiles = true;
            }
            else if(change.startsWith("#\t"))
            {
                if(newFiles)
                {
                    listener.newFile(new File(change.substring(1).trim()));
                }
            }
        }
    }
}
