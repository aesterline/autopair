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
    private Javac mainCompiler;
    private Javac testCompiler;
    private FileSystemChangeListener listener;

    public void addedFileWithJavaExtentionAndMainInPathShouldBeCompiledByMainCompiler()
    {
        File addedFile = new File("src/main/java/org/agileide/MyFile.java");
        listener.newFile(addedFile);

        verify(mainCompiler).compile(addedFile);
    }

    public void addedFileWithJavaExtentionAndTestInPathShouldBeCompiledByTestCompiler()
    {
        File addedFile = new File("src/test/java/org/agileide/MyFile.java");
        listener.newFile(addedFile);

        verify(testCompiler).compile(addedFile);
    }

    public void addedFileWithNonJavaExtentionShouldNotBeCompiled()
    {
        File addedFile = new File("pom.xml");
        listener.newFile(addedFile);

        verify(mainCompiler, never()).compile(addedFile);
        verify(testCompiler, never()).compile(addedFile);
    }

    public void changedFileWithJavaExtentionAndMainInPathShouldBeCompiledByMainCompiler()
    {
        File changedFile = new File("src/main/java/org/agileide/MyFile.java");
        listener.changedFile(changedFile);

        verify(mainCompiler).compile(changedFile);
    }

    public void changedFileWithJavaExtentionAndTestInPathShouldBeCompiledByTestCompiler()
    {
        File changedFile = new File("src/test/java/org/agileide/MyFile.java");
        listener.changedFile(changedFile);

        verify(testCompiler).compile(changedFile);
    }

    public void changedFileWithoutJavaExtentionShouldNotBeCompiled()
    {
        File changedFile = new File("pom.xml");
        listener.changedFile(changedFile);

        verify(mainCompiler, never()).compile(changedFile);
    }

    public void deletedFilesShouldNeverBeCompiled()
    {
        File deletedFile = new File("MyFile.java");
        listener.deletedFile(deletedFile);

        verify(mainCompiler, never()).compile(deletedFile);
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        mainCompiler = mock(Javac.class);
        testCompiler = mock(Javac.class);
        listener = new JavacFileSystemListener(mainCompiler, testCompiler);
    }
}
