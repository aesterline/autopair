package org.autopair.commands;

import com.google.inject.Inject;

public class GitStatus
{
    public static final String[] ARGUMENTS = {"status", "--untracked-files=all"};
    private Git git;

    @Inject
    public GitStatus(Git git)
    {
        this.git = git;
    }

    public String status()
    {
        return git.execute(ARGUMENTS);
    }
}
