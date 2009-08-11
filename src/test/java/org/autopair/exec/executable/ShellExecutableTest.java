package org.autopair.exec.executable;

import org.autopair.exec.Executable;
import org.autopair.exec.Shell;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class ShellExecutableTest
{
    private Shell shell;

    public void executeShouldExecuteCommandInShell()
    {
        String[] expected = {"git"};
        new ShellExecutable(shell, expected[0]).execute();

        verify(shell).execute(expected);
    }

    public void executeShouldReturnResultsFromShell()
    {
        String expected = "results";
        String[] command = {"git"};

        when(shell.execute(command)).thenReturn(expected);
        String results = new ShellExecutable(shell, command).execute();

        assertEquals(results, expected);
    }

    public void addArgumentsShouldNotManipulateExistingExecutable()
    {
        String[] expected = {"git"};

        Executable git = new ShellExecutable(shell, expected[0]);
        git.addArguments("status");
        git.execute();

        verify(shell).execute(expected);
    }

    public void addArgumentsShouldReturnAExecutableWithCombinedCommands()
    {
        String[] expected = {"git", "status"};

        Executable git = new ShellExecutable(shell, expected[0]);
        Executable status = git.addArguments(expected[1]);
        status.execute();

        verify(shell).execute(expected);
    }

    public void executeWithArgumentsShouldReturnExecuteShellWithCombinedCommands()
    {
        String[] expected = {"git", "status"};

        Executable git = new ShellExecutable(shell, expected[0]);
        git.execute(expected[1]);

        verify(shell).execute(expected);
    }

    public void executeWithArgumetsShouldNotManipulateExistingExecutable()
    {
        String[] expected = {"git"};

        Executable git = new ShellExecutable(shell, expected[0]);
        git.execute("status");
        git.execute();

        verify(shell).execute(expected);
    }

    public void executeWithArgumentShouldReturnResultsFromShell()
    {
        String expected = "results";
        String[] command = {"git", "status"};

        when(shell.execute(command)).thenReturn(expected);
        String results = new ShellExecutable(shell, command[0]).execute(command[1]);

        assertEquals(results, expected);
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        shell = mock(Shell.class);
    }
}
