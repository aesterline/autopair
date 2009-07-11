package org.agileide.exec;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

@Test
public class DefaultExecutableTest
{
    public void asArrayShouldExecutableAsArray()
    {
        String[] expected = {"git"};
        Executable git = new DefaultExecutable(expected[0]);

        assertEquals(git.asArray(), expected);
    }

    public void addArgumentsShouldNotManipulateExistingExecutable()
    {
        String[] expected = {"git"};
        Executable git = new DefaultExecutable(expected[0]);
        git.addArguments("status");

        assertEquals(git.asArray(), expected);
    }

    public void addArgumentsShouldReturnAExecutableWithCombinedCommands()
    {
        String[] expected = {"git", "status"};
        Executable git = new DefaultExecutable(expected[0]);
        Executable status = git.addArguments(expected[1]);

        assertEquals(status.asArray(), expected);
    }
}
