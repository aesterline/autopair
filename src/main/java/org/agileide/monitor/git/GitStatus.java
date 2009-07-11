package org.agileide.monitor.git;

import org.agileide.exec.DelegatingExecutable;
import org.agileide.exec.IExecutable;

public class GitStatus extends DelegatingExecutable
{
    public static final String[] ARGUMENTS = {"status", "--untracked-files=all"};

    public GitStatus(IExecutable gitExe)
    {
        super(gitExe.addArguments(ARGUMENTS));
    }
}
