package org.autopair.java;

import java.util.ArrayList;
import java.util.List;

import org.autopair.monitor.FileSystemChange;
import org.autopair.monitor.SystemChangeType;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class JavaFileSystemChangeFilterTest
{
    private JavaFileSystemChangeFilter filter;

    public void changeWithOutJavaExtentionShouldReturnEmptyList()
    {
        List<FileSystemChange> changes = createChanges("pom.xml");
        List<FileSystemChange> filteredChanges = filter.selectMatching(changes);

        assertTrue(filteredChanges.isEmpty(), "Only .java files should be allowed through");
    }

    public void changesWithOnlyJavaExtentionShouldReturnEntireList()
    {
        List<FileSystemChange> changes = createChanges("MyClass.java");
        List<FileSystemChange> filteredChanges = filter.selectMatching(changes);

        assertEquals(filteredChanges, changes);
    }

    public void changesWithSomeJavaAndSomeOtherShouldOnlyReturnJavaFiles()
    {
        List<FileSystemChange> changes = createChanges("MyClass.java", "pom.xml", "Join.java");
        List<FileSystemChange> filteredChanges = filter.selectMatching(changes);

        assertEquals(filteredChanges, createChanges("MyClass.java", "Join.java"));        
    }

    private List<FileSystemChange> createChanges(String... filenames)
    {
        List<FileSystemChange> changes = new ArrayList<FileSystemChange>();

        for(String filename : filenames)
        {
            changes.add(new FileSystemChange(filename, SystemChangeType.MODIFIED));
        }

        return changes;
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        filter = new JavaFileSystemChangeFilter();
    }
}
