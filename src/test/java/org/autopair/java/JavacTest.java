package org.autopair.java;

import java.io.File;

import org.autopair.exec.Executable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.testng.annotations.Test;

@Test
public class JavacTest
{
    public void compileShouldAddFileArgument()
    {
        String filename = "src/main/java/org/agileide/AutoPair.java";

        File javaFile = mock(File.class);
        when(javaFile.getAbsolutePath()).thenReturn(filename);

        Executable javacExec = mock(Executable.class);
        Executable javacWithFile = mock(Executable.class);
        when(javacExec.addArguments(filename)).thenReturn(javacWithFile);

        Javac javac = new Javac(javacExec);

        javac.compile(javaFile);

        verify(javacWithFile).execute();
    }
}
