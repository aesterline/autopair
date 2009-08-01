package org.autopair.java;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class TestPhaseTest
{
    private Tester tester;
    private TestPhase phase;

    public void testerShouldBePassedToOneProjectFile()
    {
        ProjectFile file = mock(ProjectFile.class);
        phase.execute(Arrays.asList(file));

        verify(file).test(tester);
    }

    public void testerShouldBePassedToMultipleProjectFiles()
    {
        ProjectFile[] files = {mock(ProjectFile.class), mock(ProjectFile.class)};
        phase.execute(Arrays.asList(files));

        verify(files[0]).test(tester);
        verify(files[1]).test(tester);
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        tester = mock(Tester.class);
        phase = new TestPhase(tester);
    }
}
