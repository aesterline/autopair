package org.autopair.java;

import java.util.Arrays;
import java.util.List;

public class Project
{
    private List<Phase> phases;

    public Project(Phase... phases)
    {
        this(Arrays.asList(phases));
    }

    public Project(List<Phase> phases)
    {
        this.phases = phases;
    }

    public void changedFiles(List<ProjectFile> changedFiles)
    {
        for(Phase phase : phases)
        {
            phase.execute(changedFiles);
        }
    }
}
