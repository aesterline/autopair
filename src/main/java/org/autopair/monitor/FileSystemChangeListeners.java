package org.autopair.monitor;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class FileSystemChangeListeners implements FileSystemChangeListener
{
    private Collection<FileSystemChangeListener> listeners;

    public FileSystemChangeListeners(FileSystemChangeListener... listeners)
    {
        this.listeners = Arrays.asList(listeners);
    }

    public void changes(List<FileSystemChange> changes)
    {
        for(FileSystemChangeListener listener : listeners)
        {
            listener.changes(changes);
        }
    }
}
