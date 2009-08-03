package org.autopair;

import java.io.File;
import java.util.List;

public class Project
{
    private Phase[] phases;

    public Project(Phase... phases)
    {
        this.phases = phases;
    }

    public void updates(List<File> files)
    {
        for(Phase phase : phases)
        {
            phase.execute(files);
        }
    }
}
