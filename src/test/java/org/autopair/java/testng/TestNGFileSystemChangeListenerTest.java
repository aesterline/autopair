package org.autopair.java.testng;

import java.io.File;

import org.autopair.monitor.FileSystemChangeListener;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class TestNGFileSystemChangeListenerTest
{
    private TestNg testng;
    private FileToClassName ftc;
    private FileSystemChangeListener listener;

    public void addedFileShouldBeTested()
    {
        File addedFile = new File("org/autopair/Junk.java");
        String classname = "org.autopair.Junk";

        when(ftc.convertToClassName(addedFile)).thenReturn(classname);

        listener.newFile(addedFile);

        verify(testng).test(classname);
    }

    public void changedFileShouldBeTested()
    {
        File changedFile = new File("org/autopair/Junk.java");
        String classname = "org.autopair.Junk";

        when(ftc.convertToClassName(changedFile)).thenReturn(classname);

        listener.changedFile(changedFile);

        verify(testng).test(classname);
    }

    public void deletedFileShouldNotBeTested()
    {
        File deletedFile = new File("org/autopair/Junk.java");
        listener.deletedFile(deletedFile);

        verify(testng, never()).test(anyString());
        verify(ftc, never()).convertToClassName(deletedFile);
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        testng = mock(TestNg.class);
        ftc = mock(FileToClassName.class);
        listener = new TestNGFileSystemChangeListener(testng, ftc);
    }
}
