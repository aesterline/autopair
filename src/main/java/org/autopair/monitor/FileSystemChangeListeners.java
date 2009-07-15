package org.autopair.monitor;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.collections.Closure;
import static org.apache.commons.collections.ClosureUtils.invokerClosure;
import static org.apache.commons.collections.CollectionUtils.forAllDo;

public class FileSystemChangeListeners implements FileSystemChangeListener
{
    private static final Class[] FILE_PARAM_TYPE = {File.class};

    private Collection<FileSystemChangeListener> listeners;

    public FileSystemChangeListeners(FileSystemChangeListener... listeners)
    {
        this.listeners = Arrays.asList(listeners);
    }

    public void newFile(File file)
    {
        forAllInvoke("newFile", file);
    }

    public void changedFile(File file)
    {
        forAllInvoke("changedFile", file);
    }

    public void deletedFile(File file)
    {
        forAllInvoke("deletedFile", file);
    }

    private void forAllInvoke(String methodName, File file)
    {
        Closure newFile = invokerClosure(methodName, FILE_PARAM_TYPE, new Object[] {file});
        forAllDo(listeners, newFile);
    }
}
