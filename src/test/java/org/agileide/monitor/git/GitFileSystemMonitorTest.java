package org.agileide.monitor.git;

import java.io.File;

import org.agileide.monitor.FileSystemChangeListener;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

@Test
public class GitFileSystemMonitorTest
{
    private Git git;
    private FileSystemChangeListener listener;
    private GitFileSystemMonitor monitor;

    public void unTrackedFilesShouldFireNewFileEvent()
    {
        checkForChanges(GitStatusSamples.UNTRACKED_FILE);

        verify(listener).newFile(new File("pom.xml"));
    }

    public void trackedFilesShouldFireChangedFileEvent()
    {
        checkForChanges(GitStatusSamples.TRACKED_FILE);

        verify(listener).changedFile(new File("src/test/java/org/agileide/monitor/git/GitFileSystemMonitorTest.java"));
    }

    public void multipleTrackedFileShouldFireChangedFileEventForEachChange()
    {
        checkForChanges(GitStatusSamples.MULTIPLE_TRACKED_FILES);

        verify(listener).changedFile(new File("src/main/java/org/agileide/monitor/FileSystemChangeListener.java"));
        verify(listener).changedFile(new File("src/test/java/org/agileide/monitor/git/GitStatusSamples.java"));
    }

    public void multipleUnTrackedFilesShouldFireNewFileEventForEachNewFile()
    {
        checkForChanges(GitStatusSamples.MULTIPLE_UNTRACKED_FILES);

        verify(listener).newFile(new File("pom.xml"));
        verify(listener).newFile(new File("junk/me/you/cool.txt"));
    }

    public void deletedFileShouldFireDeletedFileEvent()
    {
        checkForChanges(GitStatusSamples.DELETED_TRACKED_FILE);

        verify(listener).deletedFile(new File("pom.xml"));
    }

    public void multipleDeletedFilesShouldFireDeletedFileEventForEachDeletedFile()
    {
        checkForChanges(GitStatusSamples.MULTIPLE_DELETED_TRACKED_FILES);

        verify(listener).deletedFile(new File("pom.xml"));
        verify(listener).deletedFile(new File("src/test/java/org/agileide/monitor/git/GitStatusSamples.java"));
    }

    public void trackedAndUntrackedFilesShouldFireCorrectEvents()
    {
        checkForChanges(GitStatusSamples.MIXED_TRACKED_AND_UNTRACKED);

        verify(listener).deletedFile(new File("pom.xml"));
        verify(listener).newFile(new File("junk/for/me/yes.txt"));
    }

    public void newFilesInIndexShouldFireNewFileEvent()
    {
        checkForChanges(GitStatusSamples.NEW_FILE_IN_INDEX);

        verify(listener).newFile(new File("junk.txt"));
    }

    public void multipleFilesInIndexShouldFireCorrectEvents()
    {
        checkForChanges(GitStatusSamples.MULTIPLE_FILES_IN_INDEX);

        verify(listener).newFile(new File("junk.txt"));
        verify(listener).changedFile(new File("README"));
    }

    public void renamedFileInIndexShouldFireDeleteThenNewEvents()
    {
        checkForChanges(GitStatusSamples.RENAMED_FILE_IN_INDEX);

        verify(listener).deletedFile(new File("pom.xml"));
        verify(listener).newFile(new File("job.xml"));
    }

    private void checkForChanges(String gitStatus)
    {
        when(git.status()).thenReturn(gitStatus);
        monitor.checkForChanges();
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        git = mock(Git.class);
        listener = mock(FileSystemChangeListener.class);
        monitor = new GitFileSystemMonitor(git);

        monitor.setListener(listener);
    }
}
