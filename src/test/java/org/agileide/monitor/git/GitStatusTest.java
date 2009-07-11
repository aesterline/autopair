package org.agileide.monitor.git;

import org.agileide.exec.Executable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.testng.annotations.Test;

@Test
public class GitStatusTest
{
    public void shouldBuildStatusCommandAtConstruction()
    {
        Executable gitExe = mock(Executable.class);

        new GitStatus(gitExe);

        verify(gitExe).addArguments(GitStatus.ARGUMENTS);
    }
}
