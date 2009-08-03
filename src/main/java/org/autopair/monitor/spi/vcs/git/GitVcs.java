package org.autopair.monitor.spi.vcs.git;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import org.autopair.monitor.FileSystemChange;
import org.autopair.monitor.SystemChangeType;
import org.autopair.monitor.spi.vcs.Vcs;

public class GitVcs implements Vcs
{
    private GitStatus gitStatus;

    private static final String MODIFIED = "#\tmodified:";
    private static final String DELETED = "#\tdeleted:";
    private static final String NEW = "#\tnew file:";
    private static final String RENAMED = "#\trenamed:";

    @Inject
    public GitVcs(GitStatus gitStatus)
    {
        this.gitStatus = gitStatus;
    }

    public List<FileSystemChange> status()
    {
        String[] changes = gitStatus.status().split("\n");
        boolean newFiles = false;

        List<FileSystemChange> systemChanges = new ArrayList<FileSystemChange>();

        for(String change : changes)
        {
            if(change.startsWith(MODIFIED))
            {
                String modifiedFile = change.substring(MODIFIED.length()).trim();
                systemChanges.add(new FileSystemChange(modifiedFile, SystemChangeType.MODIFIED));
            }
            else if(change.startsWith(DELETED))
            {
                String deletedFile = change.substring(DELETED.length()).trim();
                systemChanges.add(new FileSystemChange(deletedFile, SystemChangeType.DELETED));
            }
            else if(change.startsWith(NEW))
            {
                String newFile = change.substring(NEW.length()).trim();
                systemChanges.add(new FileSystemChange(newFile, SystemChangeType.ADDED));
            }
            else if(change.startsWith(RENAMED))
            {
                String renameDefinition = change.substring(RENAMED.length()).trim();
                String[] renameParts = renameDefinition.split("->");

                String deletedFile = renameParts[0].trim();
                String addedFile = renameParts[1].trim();

                systemChanges.add(new FileSystemChange(deletedFile, SystemChangeType.DELETED));
                systemChanges.add(new FileSystemChange(addedFile, SystemChangeType.ADDED));
            }
            else if(change.contains("Untracked files"))
            {
                newFiles = true;
            }
            else if(change.startsWith("#\t"))
            {
                if(newFiles)
                {
                    String addedFile = change.substring(1).trim();
                    systemChanges.add(new FileSystemChange(addedFile, SystemChangeType.ADDED));
                }
            }
        }

        return systemChanges;
    }
}
