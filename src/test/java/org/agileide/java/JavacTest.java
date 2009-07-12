package org.agileide.java;

import java.io.File;

import org.agileide.exec.Executable;
import org.agileide.exec.Shell;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.testng.annotations.Test;

@Test
public class JavacTest
{
    public void compileShouldAddFileArgument()
    {
        String filename = "src/main/java/org/agileide/AgileIDE.java";

        File javaFile = mock(File.class);
        when(javaFile.getAbsolutePath()).thenReturn(filename);

        Executable javacExec = mock(Executable.class);
        Executable javacWithFile = mock(Executable.class);
        when(javacExec.addArguments(filename)).thenReturn(javacWithFile);

        Shell shell = mock(Shell.class);

        Javac javac = new Javac(shell, javacExec);

        javac.compile(javaFile);

        verify(shell).execute(javacWithFile);
    }
}