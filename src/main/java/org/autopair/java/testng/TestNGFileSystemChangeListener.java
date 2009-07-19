package org.autopair.java.testng;

import java.io.File;

import org.autopair.monitor.BridgeFileSystemChangeListener;

public class TestNGFileSystemChangeListener extends BridgeFileSystemChangeListener
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
