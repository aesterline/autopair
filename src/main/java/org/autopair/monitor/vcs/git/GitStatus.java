package org.autopair.monitor.vcs.git;

import com.google.inject.Inject;
import org.autopair.exec.Executable;
import org.autopair.inject.Git;

public class GitStatus
{
    public static final String[] ARGUMENTS = {"status", "--untracked-files=all"};

    private Executable status;

    @Inject
    public GitStatus(@Git Executable gitExe)
    {
        status = gitExe.addArguments(ARGUMENTS);
    }

    public String status()
    {
        return status.execute();
    }
}
