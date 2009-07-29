package org.autopair.monitor.vcs.git;

import java.util.Arrays;
import java.util.List;

import org.autopair.monitor.AddedFile;
import org.autopair.monitor.ChangedFile;
import org.autopair.monitor.DeletedFile;
import org.autopair.monitor.FileSystemChange;
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
                new AddedFile("pom.xml")
        };

        assertChangesContainsAll(changes, expectedChanges);
    }

    public void trackedFilesShouldResultInAChangedFile()
    {
        List<FileSystemChange> changes = status(GitStatusSamples.TRACKED_FILE);
        FileSystemChange[] expectedChanges = {
                new ChangedFile("src/test/java/org/agileide/monitor/git/GitVcsTest.java")
        };

        assertChangesContainsAll(changes, expectedChanges);
    }

    public void multipleTrackedFileShouldResultInAChangedFileForEachChange()
    {
        List<FileSystemChange> changes = status(GitStatusSamples.MULTIPLE_TRACKED_FILES);
        FileSystemChange[] expectedChanges = {
                new ChangedFile("src/main/java/org/agileide/monitor/FileSystemChangeListener.java"),
                new ChangedFile("src/test/java/org/agileide/monitor/git/GitStatusSamples.java")
        };

        assertChangesContainsAll(changes, expectedChanges);
    }

    public void multipleUnTrackedFilesShouldResultInAnAddedFileForEachNewFile()
    {
        List<FileSystemChange> changes = status(GitStatusSamples.MULTIPLE_UNTRACKED_FILES);
        FileSystemChange[] expectedChanges = {
                new AddedFile("pom.xml"),
                new AddedFile("junk/me/you/cool.txt")
        };

        assertChangesContainsAll(changes, expectedChanges);
    }

    public void deletedFileShouldResultInADeletedFile()
    {
        List<FileSystemChange> changes = status(GitStatusSamples.DELETED_TRACKED_FILE);
        FileSystemChange[] expectedChanges = {
                new DeletedFile("pom.xml")
        };

        assertChangesContainsAll(changes, expectedChanges);
    }

    public void multipleDeletedFilesShouldResultInADeletedFileForEachDeletedFile()
    {
        List<FileSystemChange> changes = status(GitStatusSamples.MULTIPLE_DELETED_TRACKED_FILES);
        FileSystemChange[] expectedChanges = {
                new DeletedFile("pom.xml"),
                new DeletedFile("src/test/java/org/agileide/monitor/git/GitStatusSamples.java")
        };

        assertChangesContainsAll(changes, expectedChanges);
    }

    public void trackedAndUntrackedFilesShouldTriggerCorrectFileSystemChanges()
    {
        List<FileSystemChange> changes = status(GitStatusSamples.MIXED_TRACKED_AND_UNTRACKED);
        FileSystemChange[] expectedChanges = {
                new DeletedFile("pom.xml"),
                new AddedFile("junk/for/me/yes.txt")
        };

        assertChangesContainsAll(changes, expectedChanges);
    }

    public void newFilesInIndexShouldResultInAnAddedFile()
    {
        List<FileSystemChange> changes = status(GitStatusSamples.NEW_FILE_IN_INDEX);
        FileSystemChange[] expectedChanges = {
                new AddedFile("junk.txt")
        };

        assertChangesContainsAll(changes, expectedChanges);
    }

    public void multipleFilesInIndexShouldResultInCorrectFileSystemChanges()
    {
        List<FileSystemChange> changes = status(GitStatusSamples.MULTIPLE_FILES_IN_INDEX);
        FileSystemChange[] expectedChanges = {
                new ChangedFile("README"),
                new AddedFile("junk.txt")
        };

        assertChangesContainsAll(changes, expectedChanges);
    }

    public void renamedFileInIndexShouldFireDeleteThenNewEvents()
    {
        List<FileSystemChange> changes = status(GitStatusSamples.RENAMED_FILE_IN_INDEX);
        FileSystemChange[] expectedChanges = {
                new DeletedFile("pom.xml"),
                new AddedFile("job.xml")
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