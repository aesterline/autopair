package org.autopair.monitor.spi.vcs;

import java.util.Arrays;
import java.util.List;

import org.autopair.monitor.FileSystemChange;
import org.autopair.monitor.FileSystemChangeFilter;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class VcsFileSystemMonitorSpiTest
{
    private Vcs vcs;
    private FileSystemChangeFilter changeFilter;
    private VcsFileSystemMonitorSpi monitorSpi;

    public void allChangedFilesShouldBeIncludedInTheChangeEvent()
    {
        FileSystemChange change = mock(FileSystemChange.class);
        List<FileSystemChange> changes = Arrays.asList(change);

        when(vcs.status()).thenReturn(changes);
        when(changeFilter.selectMatching(changes)).thenReturn(changes);

        assertChangesContainsAll(changes);
    }

    public void changesNotSelectedByFilterShouldNotBeReturned()
    {
        FileSystemChange change = mock(FileSystemChange.class);
        FileSystemChange oldChange = mock(FileSystemChange.class);
        List<FileSystemChange> changes = Arrays.asList(change, oldChange);
        List<FileSystemChange> selectedChanges = Arrays.asList(oldChange);

        when(vcs.status()).thenReturn(changes);
        when(changeFilter.selectMatching(changes)).thenReturn(selectedChanges);

        assertChangesContainsAll(selectedChanges);
    }

    private void assertChangesContainsAll(List<FileSystemChange> changes)
    {
        List<FileSystemChange> results = monitorSpi.checkForChanges();
        assertEquals(results, changes);
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        vcs = mock(Vcs.class);
        changeFilter = mock(FileSystemChangeFilter.class);

        monitorSpi = new VcsFileSystemMonitorSpi(vcs, changeFilter);
    }
}
