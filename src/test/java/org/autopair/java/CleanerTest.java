package org.autopair.java;

import java.io.File;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class CleanerTest
{
    private DeleteStrategy delete;
    private Cleaner cleaner;

    public void singleDirtyFileShouldBeDeleted()
    {
        File dirtyFile = new File("dirty");

        cleaner.addDirtyFiles(dirtyFile);
        cleaner.clean();

        verify(delete).delete(dirtyFile);
    }

    public void manyDirtyFilesShouldBeDeleted()
    {
        File[] dirtyFiles = {new File("dirty"), new File("clean")};

        cleaner.addDirtyFiles(dirtyFiles);
        cleaner.clean();

        for(File file : dirtyFiles)
        {
            verify(delete).delete(file);
        }
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        delete = mock(DeleteStrategy.class);
        cleaner = new Cleaner(delete);
    }
}
