package org.autopair.java.maven;

import java.io.File;
import java.util.Arrays;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class RemoveTest
{
    private Remove remove;

    public void oneFileShouldBeDeleted()
    {
        File file = mock(File.class);

        remove.all(Arrays.asList(file));

        verify(file).delete();
    }

    public void multipleFilesShouldBeDeleted()
    {
        File one = mock(File.class);
        File two = mock(File.class);

        remove.all(Arrays.asList(one, two));

        verify(one).delete();
        verify(two).delete();
    }

    public void exceptionOnDeleteShouldBeQuiet()
    {
        File file = mock(File.class);
        doThrow(new RuntimeException()).when(file).delete();

        try
        {
            remove.all(Arrays.asList(file));
            assertTrue(true, "we should make it here");
        }
        catch(Exception e)
        {
            fail("no exceptions should be thrown");
        }
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        remove = new Remove();
    }
}
