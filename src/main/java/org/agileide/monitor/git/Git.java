package org.agileide.monitor.git;

import org.agileide.exec.Shell;

public class Git
{
    private GitStatus status;
    private Shell shell;

    public Git(GitStatus status, Shell shell)
    {
        this.status = status;
        this.shell = shell;
    }

    public String status()
    {
        return shell.execute(status);
    }
}
