package org.autopair.monitor.vcs.git;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.autopair.monitor.AddedFile;
import org.autopair.monitor.ChangedFile;
import org.autopair.monitor.FileSystemChange;
import org.autopair.monitor.FileSystemChangeListener;
import org.autopair.monitor.FileSystemMonitorSpi;
import org.autopair.monitor.vcs.Vcs;

public class GitVcs implements FileSystemMonitorSpi, Vcs
{
    private GitStatus gitStatus;
    private FileSystemChangeListener listener;

    private static final String MODIFIED = "#\tmodified:";
    private static final String DELETED = "#\tdeleted:";
    private static final String NEW = "#\tnew file:";
    private static final String RENAMED = "#\trenamed:";

    public GitVcs(GitStatus gitStatus)
    {
        this.gitStatus = gitStatus;
    }

    public void setListener(FileSystemChangeListener listener)
    {
        this.listener = listener;
    }

    public List<FileSystemChange> checkForChanges()
    {
        String[] changes = gitStatus.status().split("\n");
        boolean newFiles = false;

        List<FileSystemChange> systemChanges = new ArrayList<FileSystemChange>();

        for(String change : changes)
        {
            if(change.startsWith(MODIFIED))
            {
                String modifiedFile = change.substring(MODIFIED.length()).trim();
                File changedFile = new File(modifiedFile);
                listener.changedFile(changedFile);
                systemChanges.add(new ChangedFile(changedFile));
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
                    File addedFile = new File(change.substring(1).trim());
                    listener.newFile(addedFile);
                    systemChanges.add(new AddedFile(addedFile));
                }
            }
        }

        return systemChanges;
    }

    public List<FileSystemChange> status()
    {
        return checkForChanges();
    }
}
