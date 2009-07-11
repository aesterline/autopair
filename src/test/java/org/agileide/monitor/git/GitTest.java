package org.agileide.monitor.git;

import org.agileide.Shell;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import static org.testng.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Test
public class GitTest
{
    private Shell shell;
    private Git git;

    public void statusShouldExecuteGitStatus()
    {
        git.status();
        verify(shell).execute(Git.STATUS_COMMAND);
    }

    public void statusShouldReturnResultsFromShell()
    {
        String expectedResult = "who cares";
        when(shell.execute(Git.STATUS_COMMAND)).thenReturn(expectedResult);

        String results = git.status();

        assertEquals(results, expectedResult);
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        shell = mock(Shell.class);
        git = new Git(shell);
    }
}
