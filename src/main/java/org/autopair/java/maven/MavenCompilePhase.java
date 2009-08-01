package org.autopair.java.maven;

import java.util.List;

import org.autopair.java.Phase;
import org.autopair.java.ProjectFile;

public class MavenCompilePhase implements Phase
{
    private Maven maven;

    public MavenCompilePhase(Maven maven)
    {
        this.maven = maven;
    }

    public void execute(List<ProjectFile> files)
    {
        maven.executePhase(Maven.TEST_COMPILE_PHASE);
    }
}
