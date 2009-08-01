package org.autopair.monitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class FileSystemChangeFilterChainTest
{
    private FileSystemChangeFilter filter;
    private FileSystemChangeFilter chain;

    public void emptyChainShouldReturnInitialChanges()
    {
        chain = new FileSystemChangeFilterChain();

        List<FileSystemChange> expectedChanges = new ArrayList<FileSystemChange>();
        List<FileSystemChange> changes = chain.selectMatching(expectedChanges);

        assertEquals(changes, expectedChanges);
    }

    public void chainWithOneFilterShouldDelegate()
    {
        List<FileSystemChange> changes = new ArrayList<FileSystemChange>();
        chain.selectMatching(changes);

        verify(filter).selectMatching(changes);
    }

    public void chainWithOneFilterShouldReturnDelegateSelection()
    {
        ArrayList<FileSystemChange> initialChanges = new ArrayList<FileSystemChange>();
        List<FileSystemChange> expectedSelection = Arrays.asList(new FileSystemChange("junk.txt", SystemChangeType.ADDED));
        when(filter.selectMatching(initialChanges)).thenReturn(expectedSelection);

        List<FileSystemChange> selection = chain.selectMatching(initialChanges);

        assertEquals(selection, expectedSelection);
    }

    public void multipleFiltersShouldGetCalledInOrderPassingTheResultOfOneAsTheParameterOfTheOther()
    {
        List<FileSystemChange> changes = new ArrayList<FileSystemChange>();
        FileSystemChangeFilter secondFilter = mock(FileSystemChangeFilter.class);
        chain = new FileSystemChangeFilterChain(filter, secondFilter);

        List<FileSystemChange> firstSelection = Arrays.asList((FileSystemChange) new FileSystemChange("junk.txt", SystemChangeType.ADDED));
        when(filter.selectMatching(changes)).thenReturn(firstSelection);

        chain.selectMatching(changes);

        verify(filter).selectMatching(changes);
        verify(secondFilter).selectMatching(firstSelection);
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        filter = mock(FileSystemChangeFilter.class);
        chain = new FileSystemChangeFilterChain(filter);
    }
}
