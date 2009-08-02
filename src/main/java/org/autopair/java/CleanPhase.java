package org.autopair.java;

import java.util.List;

import com.google.inject.Inject;

public class CleanPhase implements Phase
{
    private Cleaner cleaner;

    @Inject
    public CleanPhase(Cleaner cleaner)
    {
        this.cleaner = cleaner;
    }

    public void execute(List<ProjectFile> files)
    {
        for(ProjectFile file : files)
        {
            file.clean(cleaner);
        }

        cleaner.clean();
    }
}
