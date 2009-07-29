package org.autopair.java;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.testng.annotations.Test;

@Test
public class ProjectTest
{
    public void onePhaseShouldBeExecuted()
    {
        Phase phase = mock(Phase.class);
        ProjectFile file = mock(ProjectFile.class);
        List<ProjectFile> changedFiles = Arrays.asList(file);

        Project project = new Project(phase);
        project.changedFiles(changedFiles);

        verify(phase).execute(changedFiles);
    }

    public void multiplePhasesShouldBeExecuted()
    {
        Phase cleanPhase = mock(Phase.class);
        Phase compilePhase = mock(Phase.class);

        ProjectFile file = mock(ProjectFile.class);
        List<ProjectFile> changedFiles = Arrays.asList(file);

        Project project = new Project(cleanPhase, compilePhase);
        project.changedFiles(changedFiles);

        verify(cleanPhase).execute(changedFiles);
        verify(compilePhase).execute(changedFiles);
    }
}
