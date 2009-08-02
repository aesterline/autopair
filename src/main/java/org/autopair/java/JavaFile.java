package org.autopair.java;

import java.io.File;

public class JavaFile implements ProjectFile
{
    private final File javaFile;

    public JavaFile(File javaFile)
    {
        this.javaFile = javaFile;
    }

    public void clean(Cleaner cleaner)
    {
        cleaner.addDirtyFiles(javaFile);
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

        if(!this.javaFile.equals(javaFile.javaFile)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return javaFile.hashCode();
    }
}
