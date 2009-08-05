package org.autopair.monitor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class FilteredFileSystemChangeListenerTest
{
    private FileSystemChangeFilter filter;
    private FileSystemChangeListener delegatee;
    private FilteredFileSystemChangeListener listener;
    private List<FileSystemChange> systemChanges;

    public void allChangesFilteredShouldNotInvokeUnderlyingListener()
    {
        when(filter.selectMatching(systemChanges)).thenReturn(Collections.<FileSystemChange>emptyList());

        listener.changes(systemChanges);

        verify(delegatee, never()).changes(systemChanges);
    }

    public void oneMatchingChangeShouldBePassedOnToDelegatee()
    {
        when(filter.selectMatching(systemChanges)).thenReturn(systemChanges);

        listener.changes(systemChanges);

        verify(delegatee).changes(systemChanges);
    }

    public void onlyMatchingShouldBePassedOnToDelegatee()
    {
        List<FileSystemChange> expectedChanges = Collections.singletonList(new FileSystemChange("joe.txt", SystemChangeType.ADDED));
        when(filter.selectMatching(systemChanges)).thenReturn(expectedChanges);

        listener.changes(systemChanges);

        verify(delegatee).changes(expectedChanges);
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        filter = mock(FileSystemChangeFilter.class);
        delegatee = mock(FileSystemChangeListener.class);
        listener = new FilteredFileSystemChangeListener(filter, delegatee);
        systemChanges = Arrays.asList(
                new FileSystemChange("who.txt", SystemChangeType.MODIFIED),
                new FileSystemChange("you.txt", SystemChangeType.MODIFIED));
    }
}
