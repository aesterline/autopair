package org.agileide.exec;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class DelegatingExecutableTest
{
    private IExecutable delegatee;
    private IExecutable executable;

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
        delegatee = mock(IExecutable.class);
        executable = new DelegatingExecutable(delegatee);
    }
}
