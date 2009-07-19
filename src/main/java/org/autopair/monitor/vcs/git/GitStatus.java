package org.autopair.monitor.vcs.git;

import org.autopair.exec.Executable;

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
