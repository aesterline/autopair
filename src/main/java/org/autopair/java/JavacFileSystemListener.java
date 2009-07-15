package org.autopair.java;

import java.io.File;

import org.autopair.monitor.FileSystemChangeListener;

public class JavacFileSystemListener implements FileSystemChangeListener
{
    private Javac mainCompiler;
    private Javac testCompiler;

    public JavacFileSystemListener(Javac mainCompiler, Javac testCompiler)
    {
        this.mainCompiler = mainCompiler;
        this.testCompiler = testCompiler;
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
        String filename = file.getAbsolutePath();

        if(filename.endsWith(".java"))
        {
            if(filename.contains("src/main"))
            {
                mainCompiler.compile(file);
            }
            else if(filename.contains("src/test"))
            {
                testCompiler.compile(file);
            }
        }
    }

    public void deletedFile(File file)
    {
    }
}
