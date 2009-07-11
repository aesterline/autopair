package org.agileide.exec.shell;

import java.io.File;

import org.agileide.exec.ExecutableFactory;
import org.agileide.exec.IExecutable;
import org.agileide.exec.InvalidedExecutableException;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class ExecutableFactoryTest
{
    private ExecutableFactory factory;

    public void unknownExecutableShouldThrowInvalidExecutableException()
    {
        File executable = mock(File.class);
        when(executable.exists()).thenReturn(false);
        when(executable.getAbsolutePath()).thenReturn("/who/cares");

        try
        {
            factory.create(executable);
            fail("paths that don't exist should throw exception");
        }
        catch(InvalidedExecutableException e)
        {
            assertEquals(e.getMessage(), "/who/cares does not exist");
        }
    }

    public void cannotExecuteShouldThrowInvalidExecutableException()
    {
        File executable = mock(File.class);
        when(executable.exists()).thenReturn(true);
        when(executable.canExecute()).thenReturn(false);
        when(executable.getAbsolutePath()).thenReturn("/who/cares");

        try
        {
            factory.create(executable);
            fail("cannot execute should throw exception");
        }
        catch(InvalidedExecutableException e)
        {
            assertEquals(e.getMessage(), "/who/cares cannot be executed");
        }
    }

    public void executableShouldCreateExecutable()
    {
        String[] expected = {"who cares"};

        File executable = mock(File.class);
        when(executable.exists()).thenReturn(true);
        when(executable.canExecute()).thenReturn(true);
        when(executable.getAbsolutePath()).thenReturn(expected[0]);

        IExecutable exec = factory.create(executable);

        assertEquals(exec.asArray(), expected);
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        factory = new ExecutableFactory();
    }
}
