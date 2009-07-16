package org.autopair.java.testng;

import java.io.File;

import org.autopair.monitor.FileSystemChangeListener;

public class TestNGFileSystemChangeListener implements FileSystemChangeListener
{
    private TestNg testng;
    private FileToClassName ftc;

    public TestNGFileSystemChangeListener(TestNg testng, FileToClassName ftc)
    {
        this.testng = testng;
        this.ftc = ftc;
    }

    public void newFile(File file)
    {
        test(file);
    }

    public void changedFile(File file)
    {
        test(file);
    }

    public void deletedFile(File file)
    {
    }

    private void test(File file)
    {
        testng.test(ftc.convertToClassName(file));
    }
}
