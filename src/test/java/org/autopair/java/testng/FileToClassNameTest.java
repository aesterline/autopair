package org.autopair.java.testng;

import java.io.File;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class FileToClassNameTest
{
    private FileToClassName ftc;
    private String prefixPath;

    public void prefixShouldBeRemoved()
    {
        File file = new File("src/main/java/org/autopair/Junk.java");

        String classname = ftc.convertToClassName(file);
        assertFalse(classname.startsWith("src/main/java/"), "prefix should be stripped");
    }

    public void slashesShouldBeReplacedWithDots()
    {
        File file = new File("src/main/java/org/autopair/Junk.java");

        String classname = ftc.convertToClassName(file);
        assertTrue(classname.startsWith("org.autopair.Junk"), classname + ": slashes should be replaced by dots");
    }

    public void javaExtentionShouldBeRemoved()
    {
        File file = new File("src/main/java/org/autopair/Junk.java");

        String classname = ftc.convertToClassName(file);
        assertEquals(classname, "org.autopair.Junk");
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        prefixPath = new File("src/main/java/").getAbsolutePath();
        ftc = new FileToClassName(prefixPath);
    }
}
