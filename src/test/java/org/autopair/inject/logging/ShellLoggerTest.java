package org.autopair.inject.logging;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.aopalliance.intercept.MethodInvocation;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

@Test
public class ShellLoggerTest
{
    public void shellExecutionsShouldBeOutputted() throws Throwable
    {
        String command = "git --version";

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ShellLogger logger = new ShellLogger(new PrintStream(output));

        MethodInvocation invocation = mock(MethodInvocation.class);
        when(invocation.getArguments()).thenReturn(new Object[] {command.split(" ")});

        logger.invoke(invocation);

        assertEquals(output.toString(), "[shell]\t\t" + command + "\n");
    }

    public void shellExecutionShouldProceed() throws Throwable
    {
        ShellLogger logger = new ShellLogger(new PrintStream(new ByteArrayOutputStream()));

        MethodInvocation invocation = mock(MethodInvocation.class);
        when(invocation.getArguments()).thenReturn(new Object[] {"who cares".split(" ")});

        logger.invoke(invocation);

        verify(invocation).proceed();
    }
}
