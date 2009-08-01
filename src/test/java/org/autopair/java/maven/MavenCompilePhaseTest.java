package org.autopair.java.maven;

import java.util.Collections;
import java.util.List;

import org.autopair.java.ProjectFile;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.testng.annotations.Test;

@Test
public class MavenCompilePhaseTest
{
    public void shouldExceuteTestCompilePhaseWhenExecuted()
    {
        Maven maven = mock(Maven.class);
        MavenCompilePhase phase = new MavenCompilePhase(maven);

        List<ProjectFile> projectFiles = Collections.emptyList();
        phase.execute(projectFiles);

        verify(maven).executePhase(Maven.TEST_COMPILE_PHASE);
    }
}
