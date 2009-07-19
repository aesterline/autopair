package org.autopair.monitor;

import java.util.List;

public abstract class BridgeFileSystemChangeListener implements FileSystemChangeListener
{
    public void changes(List<FileSystemChange> changes)
    {
        for(FileSystemChange change : changes)
        {
            if(change instanceof AddedFile)
            {
                newFile(change.getFile());
            }
            else if(change instanceof ChangedFile)
            {
                changedFile(change.getFile());
            }
            else if(change instanceof DeletedFile)
            {
                deletedFile(change.getFile());
            }
        }
    }
}
