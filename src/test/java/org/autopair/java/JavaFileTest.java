package org.autopair.java;

import java.io.File;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import org.testng.annotations.Test;

@Test
public class JavaFileTest
{
    public void cleanShouldAddFileToCleaner()
    {
        File file = new File("My.java");
        JavaFile javaFile = new JavaFile(file);

        Cleaner cleaner = mock(Cleaner.class);
        javaFile.clean(cleaner);

        verify(cleaner).addDirtyFiles(file);
    }

    public void twoJavaFilesShouldBeEqualWhenTheirUnderlyingFilesAreEqual()
    {
        File file = new File("My.java");
        JavaFile lhs = new JavaFile(file);
        JavaFile rhs = new JavaFile(file);

        assertEquals(lhs, rhs);
    }

    public void twoJavaFilesShouldNotBeEqualWhenTheirUnderlyingFilesAreDifferent()
    {
        JavaFile lhs = new JavaFile(new File("My.java"));
        JavaFile rhs = new JavaFile(new File("Your.java"));

        assertFalse(lhs.equals(rhs), "The java files should not be equal");
    }
}
