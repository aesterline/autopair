package org.autopair.java;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class CleanPhaseTest
{
    private Cleaner cleaner;
    private ProjectFile file;
    private Phase phase;

    public void cleanerShouldBePassedToAllProjectFiles()
    {
        phase.execute(Arrays.asList(file));

        verify(file).clean(cleaner);
    }

    public void cleanShouldBeCalled()
    {
        phase.execute(Arrays.asList(file));

        verify(cleaner).clean();
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        cleaner = mock(Cleaner.class);
        file = mock(ProjectFile.class);
        phase = new CleanPhase(cleaner);
    }
}
