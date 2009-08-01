package org.autopair.monitor;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class FileSystemChangeListenersTest
{
    private FileSystemChangeListener compilerListener;
    private FileSystemChangeListener testListener;
    private FileSystemChangeListener listeners;

    public void allListenersShouldBeNotifiedOfChanges()
    {
        List<FileSystemChange> changes = Arrays.asList(mock(FileSystemChange.class));

        listeners.changes(changes);

        verify(compilerListener).changes(changes);
        verify(testListener).changes(changes);
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        compilerListener = mock(FileSystemChangeListener.class);
        testListener = mock(FileSystemChangeListener.class);
        listeners = new FileSystemChangeListeners(compilerListener, testListener);
    }
}
