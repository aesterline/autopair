package org.autopair.monitor;

import java.io.File;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import org.testng.annotations.Test;

@Test
public class FileSystemChangeTest
{
    public void lastUpdatedTimeShouldBeBasedOnFileModifiedTime()
    {
        long lastModifiedTime = 1500L;

        File file = mock(File.class);
        when(file.lastModified()).thenReturn(lastModifiedTime);

        FileSystemChange change = new FileSystemChange(file, SystemChangeType.ADDED);

        assertEquals(change.lastModifiedTime(), lastModifiedTime);
    }

    public void changesAreEqualIfTheirFilesAndTypesAreTheSame()
    {
        File file = new File("me.txt");

        FileSystemChange lhs = new FileSystemChange(file, SystemChangeType.ADDED);
        FileSystemChange rhs = new FileSystemChange(file, SystemChangeType.ADDED);

        assertEquals(lhs, rhs);
    }

    public void changesAreNotEqualIfTheirFilesAreDifferent()
    {
        FileSystemChange lhs = new FileSystemChange(new File("me.txt"), SystemChangeType.ADDED);
        FileSystemChange rhs = new FileSystemChange(new File("you.txt"), SystemChangeType.ADDED);

        assertFalse(lhs.equals(rhs), "Changes should not be equal");
    }

    public void changesAreNotEqualIfTheirTypesAreNotTheSame()
    {
        File file = new File("me.txt");

        FileSystemChange lhs = new FileSystemChange(file, SystemChangeType.ADDED);
        FileSystemChange rhs = new FileSystemChange(file, SystemChangeType.MODIFIED);

        assertFalse(lhs.equals(rhs), "Changes should not be equal");
    }
}
