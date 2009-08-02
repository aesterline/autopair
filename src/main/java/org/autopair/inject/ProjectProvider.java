package org.autopair.inject;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.autopair.java.CleanPhase;
import org.autopair.java.Project;

public class ProjectProvider implements Provider<Project>
{
    private CleanPhase clean;

    @Inject
    public ProjectProvider(CleanPhase clean)
    {
        this.clean = clean;
    }

    public Project get()
    {
        return new Project(clean);
    }
}
