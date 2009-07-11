package org.agileide.monitor.git;

import java.io.File;

import org.agileide.monitor.FileSystemChangeListener;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.testng.annotations.Test;

@Test
public class GitFileSystemMonitorTest
{
    public void statusWithUnTrackedFilesShouldFireNewFileEvent()
    {
        Git git = mock(Git.class);

        FileSystemChangeListener listener = mock(FileSystemChangeListener.class);
        GitFileSystemMonitor monitor = new GitFileSystemMonitor(git);
        monitor.addListener(listener);

        when(git.status()).thenReturn(GitStatusSamples.UNTRACKED_FILES);
        monitor.checkForChanges();

        verify(listener).newFile(new File("pom.xml"));
    }
}
