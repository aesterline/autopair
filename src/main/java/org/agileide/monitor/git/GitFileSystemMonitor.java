package org.agileide.monitor.git;

import java.io.File;

import org.agileide.monitor.FileSystemChangeListener;

public class GitFileSystemMonitor
{
    private Git git;
    private FileSystemChangeListener listener;

    private static final String MODIFIED = "#\tmodified:";
    private static final String DELETED = "#\tdeleted:";

    public GitFileSystemMonitor(Git git)
    {
        this.git = git;
    }

    public void addListener(FileSystemChangeListener listener)
    {
        this.listener = listener;
    }

    public void checkForChanges()
    {
        String[] changes = git.status().split("\n");
        boolean newFiles = false;

        for(String change : changes)
        {
            if(change.startsWith(MODIFIED))
            {
               listener.changedFile(new File(change.substring(MODIFIED.length()).trim()));
            }
            else if(change.startsWith(DELETED))
            {
                listener.deletedFile(new File(change.substring(DELETED.length()).trim()));
            }
            else if(change.contains("Untracked files")) newFiles = true;
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
