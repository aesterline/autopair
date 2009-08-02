package org.autopair.java.maven;

import java.io.File;

import org.autopair.java.IgnoredFile;
import org.autopair.java.JavaFile;
import org.autopair.java.ProjectFile;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class MavenProjectFileFactoryTest
{
    private MavenProjectFileFactory factory;

    public void filesWithJavaExtentionShouldReturnJavaFile()
    {
        ProjectFile file = factory.create(new File("MyClass.java"));

        assertEquals(file.getClass(), JavaFile.class);
    }

    public void filesWithNonJavaExtentionShouldReturnIgnoredFile()
    {
        ProjectFile file = factory.create(new File("pom.xml"));

        assertEquals(file.getClass(), IgnoredFile.class);
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        factory = new MavenProjectFileFactory();
    }
}
