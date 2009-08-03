package org.autopair.monitor.spi.vcs.git;

import java.util.Arrays;
import java.util.List;

import org.autopair.monitor.FileSystemChange;
import org.autopair.monitor.SystemChangeType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class GitVcsTest
{
    private GitStatus status;
    private GitVcs vcs;

    public void unTrackedFilesResultInAnAddedFile()
    {
        List<FileSystemChange> changes = status(GitStatusSamples.UNTRACKED_FILE);
        FileSystemChange[] expectedChanges = {
                new FileSystemChange("pom.xml", SystemChangeType.ADDED)
        };

        assertChangesContainsAll(changes, expectedChanges);
    }

    public void trackedFilesShouldResultInAChangedFile()
    {
        List<FileSystemChange> changes = status(GitStatusSamples.TRACKED_FILE);
        FileSystemChange[] expectedChanges = {
                new FileSystemChange("src/test/java/org/agileide/monitor/git/GitVcsTest.java", SystemChangeType.MODIFIED)
        };

        assertChangesContainsAll(changes, expectedChanges);
    }

    public void multipleTrackedFileShouldResultInAChangedFileForEachChange()
    {
        List<FileSystemChange> changes = status(GitStatusSamples.MULTIPLE_TRACKED_FILES);
        FileSystemChange[] expectedChanges = {
                new FileSystemChange("src/main/java/org/agileide/monitor/FileSystemChangeListener.java", SystemChangeType.MODIFIED),
                new FileSystemChange("src/test/java/org/agileide/monitor/git/GitStatusSamples.java", SystemChangeType.MODIFIED)
        };

        assertChangesContainsAll(changes, expectedChanges);
    }

    public void multipleUnTrackedFilesShouldResultInAnAddedFileForEachNewFile()
    {
        List<FileSystemChange> changes = status(GitStatusSamples.MULTIPLE_UNTRACKED_FILES);
        FileSystemChange[] expectedChanges = {
                new FileSystemChange("pom.xml", SystemChangeType.ADDED),
                new FileSystemChange("junk/me/you/cool.txt", SystemChangeType.ADDED)
        };

        assertChangesContainsAll(changes, expectedChanges);
    }

    public void deletedFileShouldResultInADeletedFile()
    {
        List<FileSystemChange> changes = status(GitStatusSamples.DELETED_TRACKED_FILE);
        FileSystemChange[] expectedChanges = {
                new FileSystemChange("pom.xml", SystemChangeType.DELETED)
        };

        assertChangesContainsAll(changes, expectedChanges);
    }

    public void multipleDeletedFilesShouldResultInADeletedFileForEachDeletedFile()
    {
        List<FileSystemChange> changes = status(GitStatusSamples.MULTIPLE_DELETED_TRACKED_FILES);
        FileSystemChange[] expectedChanges = {
                new FileSystemChange("pom.xml", SystemChangeType.DELETED),
                new FileSystemChange("src/test/java/org/agileide/monitor/git/GitStatusSamples.java", SystemChangeType.DELETED)
        };

        assertChangesContainsAll(changes, expectedChanges);
    }

    public void trackedAndUntrackedFilesShouldTriggerCorrectFileSystemChanges()
    {
        List<FileSystemChange> changes = status(GitStatusSamples.MIXED_TRACKED_AND_UNTRACKED);
        FileSystemChange[] expectedChanges = {
                new FileSystemChange("pom.xml", SystemChangeType.DELETED),
                new FileSystemChange("junk/for/me/yes.txt", SystemChangeType.ADDED)
        };

        assertChangesContainsAll(changes, expectedChanges);
    }

    public void newFilesInIndexShouldResultInAnAddedFile()
    {
        List<FileSystemChange> changes = status(GitStatusSamples.NEW_FILE_IN_INDEX);
        FileSystemChange[] expectedChanges = {
                new FileSystemChange("junk.txt", SystemChangeType.ADDED)
        };

        assertChangesContainsAll(changes, expectedChanges);
    }

    public void multipleFilesInIndexShouldResultInCorrectFileSystemChanges()
    {
        List<FileSystemChange> changes = status(GitStatusSamples.MULTIPLE_FILES_IN_INDEX);
        FileSystemChange[] expectedChanges = {
                new FileSystemChange("README", SystemChangeType.MODIFIED),
                new FileSystemChange("junk.txt", SystemChangeType.ADDED)
        };

        assertChangesContainsAll(changes, expectedChanges);
    }

    public void renamedFileInIndexShouldFireDeleteThenNewEvents()
    {
        List<FileSystemChange> changes = status(GitStatusSamples.RENAMED_FILE_IN_INDEX);
        FileSystemChange[] expectedChanges = {
                new FileSystemChange("pom.xml", SystemChangeType.DELETED),
                new FileSystemChange("job.xml", SystemChangeType.ADDED)
        };

        assertChangesContainsAll(changes, expectedChanges);
    }

    private List<FileSystemChange> status(String gitStatus)
    {
        when(status.status()).thenReturn(gitStatus);
        return vcs.status();
    }

    private void assertChangesContainsAll(List<FileSystemChange> changes, FileSystemChange... expected)
    {
        assertEquals(changes, Arrays.asList(expected));
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        status = mock(GitStatus.class);
        vcs = new GitVcs(status);
    }
}
