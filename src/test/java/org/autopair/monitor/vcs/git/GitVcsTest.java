package org.autopair.monitor.vcs.git;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.autopair.monitor.AddedFile;
import org.autopair.monitor.ChangedFile;
import org.autopair.monitor.FileSystemChange;
import org.autopair.monitor.FileSystemChangeListener;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class GitVcsTest
{
    private GitStatus status;
    private FileSystemChangeListener listener;
    private GitVcs vcs;

    public void unTrackedFilesResultInAnAddedFile()
    {
        List<FileSystemChange> changes = status(GitStatusSamples.UNTRACKED_FILE);

        assertChangesContainsAll(changes, new AddedFile(new File("pom.xml")));
    }

    public void trackedFilesShouldResultInAChangedFile()
    {
        List<FileSystemChange> changes = status(GitStatusSamples.TRACKED_FILE);

        assertChangesContainsAll(changes, new ChangedFile(new File("src/test/java/org/agileide/monitor/git/GitVcsTest.java")));
    }

    public void multipleTrackedFileShouldFireChangedFileEventForEachChange()
    {
        status(GitStatusSamples.MULTIPLE_TRACKED_FILES);

        verify(listener).changedFile(new File("src/main/java/org/agileide/monitor/FileSystemChangeListener.java"));
        verify(listener).changedFile(new File("src/test/java/org/agileide/monitor/git/GitStatusSamples.java"));
    }

    public void multipleUnTrackedFilesShouldFireNewFileEventForEachNewFile()
    {
        status(GitStatusSamples.MULTIPLE_UNTRACKED_FILES);

        verify(listener).newFile(new File("pom.xml"));
        verify(listener).newFile(new File("junk/me/you/cool.txt"));
    }

    public void deletedFileShouldFireDeletedFileEvent()
    {
        status(GitStatusSamples.DELETED_TRACKED_FILE);

        verify(listener).deletedFile(new File("pom.xml"));
    }

    public void multipleDeletedFilesShouldFireDeletedFileEventForEachDeletedFile()
    {
        status(GitStatusSamples.MULTIPLE_DELETED_TRACKED_FILES);

        verify(listener).deletedFile(new File("pom.xml"));
        verify(listener).deletedFile(new File("src/test/java/org/agileide/monitor/git/GitStatusSamples.java"));
    }

    public void trackedAndUntrackedFilesShouldFireCorrectEvents()
    {
        status(GitStatusSamples.MIXED_TRACKED_AND_UNTRACKED);

        verify(listener).deletedFile(new File("pom.xml"));
        verify(listener).newFile(new File("junk/for/me/yes.txt"));
    }

    public void newFilesInIndexShouldFireNewFileEvent()
    {
        status(GitStatusSamples.NEW_FILE_IN_INDEX);

        verify(listener).newFile(new File("junk.txt"));
    }

    public void multipleFilesInIndexShouldFireCorrectEvents()
    {
        status(GitStatusSamples.MULTIPLE_FILES_IN_INDEX);

        verify(listener).newFile(new File("junk.txt"));
        verify(listener).changedFile(new File("README"));
    }

    public void renamedFileInIndexShouldFireDeleteThenNewEvents()
    {
        status(GitStatusSamples.RENAMED_FILE_IN_INDEX);

        verify(listener).deletedFile(new File("pom.xml"));
        verify(listener).newFile(new File("job.xml"));
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
        listener = mock(FileSystemChangeListener.class);
        vcs = new GitVcs(status);

        vcs.setListener(listener);
    }
}
