package org.agileide.monitor.git;

public class Git
{
    private GitStatus status;

    public Git(GitStatus status)
    {
        this.status = status;
    }

    public String status()
    {
        return status.execute();
    }
}
