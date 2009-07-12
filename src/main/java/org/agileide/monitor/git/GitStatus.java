package org.agileide.monitor.git;

import org.agileide.exec.Executable;

public class GitStatus
{
    public static final String[] ARGUMENTS = {"status", "--untracked-files=all"};

    private Executable status;

    public GitStatus(Executable gitExe)
    {
        status = gitExe.addArguments(ARGUMENTS);
    }

    public String status()
    {
        return status.execute();
    }
}
