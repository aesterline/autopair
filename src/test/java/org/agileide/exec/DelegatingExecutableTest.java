package org.agileide.exec;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class DelegatingExecutableTest
{
    private Executable delegatee;
    private Executable executable;

    public void asArrayShouldBeDelegated()
    {
        executable.asArray();

        verify(delegatee).asArray();
    }

    public void addArgumentsShouldBeDelegated()
    {
        String[] arguments = {"git", "status"};

        executable.addArguments(arguments);

        verify(delegatee).addArguments(arguments);
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        delegatee = mock(Executable.class);
        executable = new DelegatingExecutable(delegatee);
    }
}