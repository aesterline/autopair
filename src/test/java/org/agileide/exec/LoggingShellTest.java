package org.agileide.exec;

import org.apache.commons.logging.Log;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class LoggingShellTest
{
    private Log log;
    private Shell delegatee;
    private Shell shell;

    public void executeShouldLogCommand()
    {
        shell.execute("git", "status");

        verify(log).info("executing: git status");
    }

    public void executeShouldDelegate()
    {
        String[] expectedCommand = {"git", "status"};
        String expectedResult = "status";
        when(delegatee.execute(expectedCommand)).thenReturn(expectedResult);

        String result = shell.execute(expectedCommand);

        verify(delegatee).execute(expectedCommand);
        assertEquals(result, expectedResult);
    }

    public void executeShouldLogResult()
    {
        String[] expectedCommand = {"git", "status"};
        String expectedResult = "status";
        when(delegatee.execute(expectedCommand)).thenReturn(expectedResult);

        shell.execute(expectedCommand);

        verify(log).info("git status\n\tstatus");
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        log = mock(Log.class);
        delegatee = mock(Shell.class);
        shell = new LoggingShell(delegatee, log);
    }
}
