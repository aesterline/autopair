package org.agileide.java;

import java.io.File;

import org.agileide.exec.Executable;

public class Javac
{
    private Executable javacExec;

    public Javac(Executable javacExec)
    {
        this.javacExec = javacExec;
    }

    public void compile(File file)
    {
        String filename = file.getAbsolutePath();
        Executable javac = javacExec.addArguments(filename);
        javac.execute();
    }
}
