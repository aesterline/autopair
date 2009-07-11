package org.agileide.java;

import java.io.File;

import org.agileide.monitor.FileSystemChangeListener;

public class JavacFileSystemListener implements FileSystemChangeListener
{
    private Javac javac;

    public JavacFileSystemListener(Javac javac)
    {
        this.javac = javac;
    }

    public void newFile(File file)
    {
        compile(file);
    }

    public void changedFile(File file)
    {
        compile(file);
    }

    private void compile(File file)
    {
        if(file.getName().endsWith(".java"))
        {
            javac.compile(file);
        }
    }

    public void deletedFile(File file)
    {
    }
}
