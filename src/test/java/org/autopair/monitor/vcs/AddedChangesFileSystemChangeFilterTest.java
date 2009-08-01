package org.autopair.monitor.vcs;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.autopair.monitor.FileSystemChange;
import org.autopair.monitor.FileSystemChangeFilter;
import org.autopair.monitor.SystemChangeType;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class AddedChangesFileSystemChangeFilterTest
{
    private FileSystemChangeFilter filter;

    public void firstTimeSeeingAnAddedFileShouldSelectAsAddedFile()
    {
        FileSystemChange added = new FileSystemChange("junk.txt", SystemChangeType.ADDED);
        List<FileSystemChange> changes = Collections.singletonList(added);
        List<FileSystemChange> selected = filter.selectMatching(changes);

        assertEquals(selected, changes);
    }

    public void secondTimeSeeingAnAddedFileShouldBeConvertedToAChangedFile()
    {
        FileSystemChange added = new FileSystemChange("junk.txt", SystemChangeType.ADDED);
        List<FileSystemChange> changes = Collections.singletonList(added);

        filter.selectMatching(changes);
        List<FileSystemChange> selected = filter.selectMatching(changes);

        assertEquals(selected, Arrays.asList(new FileSystemChange(added.getFile(), SystemChangeType.MODIFIED)));
    }

    public void addedFilesNotSeenShouldBeRemovedFromCache()
    {
        FileSystemChange added = new FileSystemChange("junk.txt", SystemChangeType.ADDED);
        List<FileSystemChange> changes = Collections.singletonList(added);
        List<FileSystemChange> emptyChanges = Collections.emptyList();

        filter.selectMatching(changes);
        filter.selectMatching(emptyChanges);
        List<FileSystemChange> selected = filter.selectMatching(changes);

        assertEquals(selected, changes);
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        filter = new AddedChangesFileSystemChangeFilter();
    }
}
