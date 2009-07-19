package org.autopair.monitor.vcs;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.autopair.monitor.AddedFile;
import org.autopair.monitor.ChangedFile;
import org.autopair.monitor.FileSystemChange;
import org.autopair.monitor.FileSystemChangeFilter;
import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class AddedChangesFileSystemChangeFilterTest
{
    private FileSystemChangeFilter filter;

    public void firstTimeSeeingAnAddedFileShouldSelectAsAddedFile()
    {
        FileSystemChange added = mock(AddedFile.class);
        List<FileSystemChange> changes = Collections.singletonList(added);
        List<FileSystemChange> selected = filter.selectMatching(changes);

        assertEquals(selected, changes);
    }

    public void secondTimeSeeingAnAddedFileShouldBeConvertedToAChangedFile()
    {
        AddedFile added = new AddedFile(new File("junk.txt"));
        List<FileSystemChange> changes = Collections.singletonList((FileSystemChange) added);

        filter.selectMatching(changes);
        List<FileSystemChange> selected = filter.selectMatching(changes);

        assertEquals(selected, Arrays.asList(new ChangedFile(added)));
    }

    public void addedFilesNotSeenShouldBeRemovedFromCache()
    {
        AddedFile added = mock(AddedFile.class);
        List<FileSystemChange> changes = Collections.singletonList((FileSystemChange) added);
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
