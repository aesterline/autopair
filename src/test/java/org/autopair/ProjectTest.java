package org.autopair;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.testng.annotations.Test;

@Test
public class ProjectTest
{
    public void projectShouldExecuteOnePhase()
    {
        Phase phase = mock(Phase.class);
        List<File> updates = Arrays.asList(new File("MyClass.java"));

        Project project = new Project(phase);
        project.updates(updates);

        verify(phase).execute(updates);
    }

    public void projectShouldExecuteAllPhases()
    {
        Phase cleanPhase = mock(Phase.class);
        Phase compilePhase = mock(Phase.class);

        List<File> updates = Arrays.asList(new File("MyClass.java"));

        Project project = new Project(cleanPhase, compilePhase);
        project.updates(updates);

        verify(cleanPhase).execute(updates);
        verify(compilePhase).execute(updates);
    }
}
