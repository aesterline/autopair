package org.autopair.commands;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class GitStatusTest
{
    private Git git;
    private GitStatus status;

    public void shouldExecuteStatus()
    {
        status.status();

        verify(git).execute(GitStatus.ARGUMENTS);
    }

    public void shouldReturnResultsFromStatus()
    {
        String expectedResult = "results";
        when(git.execute(GitStatus.ARGUMENTS)).thenReturn(expectedResult);

        String result = status.status();

        assertEquals(result, expectedResult);
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        git = mock(Git.class);
        status = new GitStatus(git);
    }
}
