package org.agileide.monitor.git;

import org.agileide.exec.DelegatingExecutable;
import org.agileide.exec.Executable;

public class GitStatus extends DelegatingExecutable
{
    public static final String[] ARGUMENTS = {"status", "--untracked-files=all"};

    public GitStatus(Executable gitExe)
    {
        super(gitExe.addArguments(ARGUMENTS));
    }
}
