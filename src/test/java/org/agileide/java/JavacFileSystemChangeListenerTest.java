package org.agileide.java;

import java.io.File;

import org.agileide.monitor.FileSystemChangeListener;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class JavacFileSystemChangeListenerTest
{
    private Javac javac;
    private FileSystemChangeListener listener;

    public void addedFileWithJavaExtentionShouldBeCompiled()
    {
        File addedFile = new File("MyFile.java");
        listener.newFile(addedFile);

        verify(javac).compile(addedFile);
    }

    public void addedFileWithNonJavaExtentionShouldNotBeCompiled()
    {
        File addedFile = new File("pom.xml");
        listener.newFile(addedFile);

        verify(javac, never()).compile(addedFile);
    }

    public void changedFileWithJavaExtentionShouldBeCompiled()
    {
        File changedFile = new File("MyFile.java");
        listener.changedFile(changedFile);

        verify(javac).compile(changedFile);
    }

    public void changedFileWithoutJavaExtentionShouldNotBeCompiled()
    {
        File changedFile = new File("pom.xml");
        listener.changedFile(changedFile);

        verify(javac, never()).compile(changedFile);
    }

    public void deletedFilesShouldNeverBeCompiled()
    {
        File deletedFile = new File("MyFile.java");
        listener.deletedFile(deletedFile);

        verify(javac, never()).compile(deletedFile);
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        javac = mock(Javac.class);
        listener = new JavacFileSystemListener(javac);
    }
}
