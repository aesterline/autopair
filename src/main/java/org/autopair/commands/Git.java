package org.autopair.commands;

import org.autopair.exec.Executable;

public class Git
{
    private Executable gitExe;

    public Git(Executable gitExe)
    {
        this.gitExe = gitExe;
    }

    public String execute(String... arguments)
    {
        return gitExe.execute(arguments);
    }
}
