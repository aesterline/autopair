package org.agileide.monitor.git;

import org.agileide.Shell;

public class Git
{
    public static final String STATUS_COMMAND = "git status --untracked-files=all";

    private Shell shell;

    public Git(Shell shell)
    {
        this.shell = shell;
    }

    public String status()
    {
        return shell.execute(STATUS_COMMAND);
    }
}
