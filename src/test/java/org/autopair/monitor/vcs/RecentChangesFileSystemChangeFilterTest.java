package org.autopair.monitor.vcs;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.autopair.monitor.FileSystemChange;
import org.autopair.monitor.FileSystemChangeFilter;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class RecentChangesFileSystemChangeFilterTest
{
    private Clock clock;
    private FileSystemChangeFilter filter;

    public void changesSinceAfterCurrentMarkShouldBeSelected()
    {
        FileSystemChange change = mock(FileSystemChange.class);
        List<FileSystemChange> unfilteredChanges = Arrays.asList(change);

        when(clock.currentMark()).thenReturn(1l);
        when(change.lastModifiedTime()).thenReturn(2l);

        assertFilterSelected(unfilteredChanges, change);
    }

    public void changesBeforeCurrentMarkShouldNotBeSelected()
    {
        FileSystemChange oldChange = mock(FileSystemChange.class);
        FileSystemChange newChange = mock(FileSystemChange.class);

        List<FileSystemChange> unfilteredChanges = Arrays.asList(oldChange, newChange);

        when(clock.currentMark()).thenReturn(2l);
        when(oldChange.lastModifiedTime()).thenReturn(1l);
        when(newChange.lastModifiedTime()).thenReturn(3l);

        assertFilterSelected(unfilteredChanges, newChange);
    }

    public void clockShouldBeMarkedAfterEachSelection()
    {
        List<FileSystemChange> changes = Collections.emptyList();
        filter.selectMatching(changes);

        verify(clock).mark();
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        clock = mock(Clock.class);
        filter = new RecentChangesFileSystemChangeFilter(clock);
    }

    private void assertFilterSelected(List<FileSystemChange> unfilteredChanges, FileSystemChange... expecteChanges)
    {
        List<FileSystemChange> selected = filter.selectMatching(unfilteredChanges);
        assertEquals(selected, Arrays.asList(expecteChanges));
    }
}
