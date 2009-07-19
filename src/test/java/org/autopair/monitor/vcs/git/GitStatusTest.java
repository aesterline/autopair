package org.autopair.monitor.vcs.git;

import org.autopair.exec.Executable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class GitStatusTest
{
    private Executable gitExe;

    public void shouldBuildStatusExecutableAtConstruction()
    {
        new GitStatus(gitExe);

        verify(gitExe).addArguments(GitStatus.ARGUMENTS);
    }

    public void statusShouldExecuteStatusExecutable()
    {
        String expected = "result";

        Executable statusExecutable = mock(Executable.class);
        when(gitExe.addArguments(GitStatus.ARGUMENTS)).thenReturn(statusExecutable);
        when(statusExecutable.execute()).thenReturn(expected);

        String results = new GitStatus(gitExe).status();

        verify(statusExecutable).execute();
        assertEquals(results, expected);
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        gitExe = mock(Executable.class);
    }
}
