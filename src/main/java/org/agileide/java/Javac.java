package org.agileide.java;

import java.io.File;

import org.agileide.exec.Executable;
import org.agileide.exec.Shell;

public class Javac
{
    private Shell shell;
    private Executable javacExec;

    public Javac(Shell shell, Executable javacExec)
    {
        this.shell = shell;
        this.javacExec = javacExec;
    }

    public void compile(File file)
    {
        String filename = file.getAbsolutePath();
        shell.execute(javacExec.addArguments(filename));
    }
}
