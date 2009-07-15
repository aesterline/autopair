package org.autopair.monitor;

import java.io.File;

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

    public void allListenersShouldBeNotifiedWhenFileAdded()
    {
        File addedFile = new File("MyTest.java");

        listeners.newFile(addedFile);

        verify(compilerListener).newFile(addedFile);
        verify(testListener).newFile(addedFile);
    }

    public void allListenersShouldBeNotifiedWhenFileChanged()
    {
        File changedFile = new File("MyTest.java");

        listeners.changedFile(changedFile);

        verify(compilerListener).changedFile(changedFile);
        verify(testListener).changedFile(changedFile);
    }

    public void allListenersShouldBeNotifiedWhenFileDeleted()
    {
        File deletedFile = new File("MyTest.java");

        listeners.deletedFile(deletedFile);

        verify(compilerListener).deletedFile(deletedFile);
        verify(testListener).deletedFile(deletedFile);
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        compilerListener = mock(FileSystemChangeListener.class);
        testListener = mock(FileSystemChangeListener.class);
        listeners = new FileSystemChangeListeners(compilerListener, testListener);
    }
}
