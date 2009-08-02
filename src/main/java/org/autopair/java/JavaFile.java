package org.autopair.java;

import java.io.File;

public class JavaFile implements ProjectFile
{
    private final File changedFile;

    public JavaFile(File changedFile)
    {
        this.changedFile = changedFile;
    }

    public void clean(Cleaner cleaner)
    {
    }

    public void test(Tester tester)
    {
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        JavaFile javaFile = (JavaFile) o;

        if(!changedFile.equals(javaFile.changedFile)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return changedFile.hashCode();
    }
}
