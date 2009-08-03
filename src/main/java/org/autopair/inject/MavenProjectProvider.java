package org.autopair.inject;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.autopair.Project;
import org.autopair.java.maven.MavenCleanPhase;

public class MavenProjectProvider implements Provider<Project>
{
    private MavenCleanPhase clean;

    @Inject
    public MavenProjectProvider(MavenCleanPhase clean)
    {
        this.clean = clean;
    }

    public Project get()
    {
        return new Project(clean);
    }
}
