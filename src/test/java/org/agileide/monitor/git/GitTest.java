package org.agileide.monitor.git;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class GitTest
{
    private Git git;
    private GitStatus status;

    public void statusShouldExecuteGitStatus()
    {
        git.status();
        verify(status).execute();
    }

    public void statusShouldReturnResultsFromShell()
    {
        String expectedResult = "who cares";
        when(status.execute()).thenReturn(expectedResult);

        String results = git.status();

        assertEquals(results, expectedResult);
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        status = mock(GitStatus.class);
        git = new Git(status);
    }
}
