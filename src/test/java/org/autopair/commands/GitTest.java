package org.autopair.commands;

import org.autopair.exec.Executable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class GitTest
{
    private Executable gitExe;
    private Git git;

    public void shouldExecuteAllArgumentsAgainstTheExecutable()
    {
        String[] arguments = {"--version"};
        git.execute(arguments);

        verify(gitExe).execute(arguments);
    }

    public void shouldReturnResultsFromExecutable()
    {
        String[] arguments = {"--version"};
        String expectedResult = "result";
        when(gitExe.execute(arguments)).thenReturn(expectedResult);

        String result = git.execute(arguments);

        assertEquals(result, expectedResult);
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        gitExe = mock(Executable.class);
        git = new Git(gitExe);
    }
}
