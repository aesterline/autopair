package org.autopair.java.testng;

import java.io.File;
import java.util.List;

import org.autopair.monitor.FileSystemChange;
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

    public void changes(List<FileSystemChange> changes)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private void test(File file)
    {
        testng.test(ftc.convertToClassName(file));
    }
}
