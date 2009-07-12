package org.agileide.exec.shell;

import org.apache.commons.io.IOUtils;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class ProcessShellTest
{
    private ProcessFactory factory;
    private Process process;
    private ProcessShell shell;

    private static final String[] COMMAND = {"git", "status"};

    public void commandShouldBePassedToTheProcessFactory()
    {
        when(process.getInputStream()).thenReturn(IOUtils.toInputStream("who cares"));

        shell.execute(COMMAND);

        verify(factory).create(COMMAND);
    }

    public void outputFromTheProcessShouldBeFullyReadAndReturned()
    {
        String expected = "Does this work";
        when(process.getInputStream()).thenReturn(IOUtils.toInputStream(expected));

        String results = shell.execute(COMMAND);

        assertEquals(results, expected);
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        factory = mock(ProcessFactory.class);
        process = mock(Process.class);

        when(factory.create(COMMAND)).thenReturn(process);

        shell = new ProcessShell(factory);
    }
}
