package org.autopair.inject.vcs;

import com.google.inject.Binder;
import com.google.inject.Module;
import org.autopair.CurrentDirectory;
import org.autopair.monitor.spi.vcs.Vcs;
import org.autopair.monitor.spi.vcs.git.GitVcs;

public class VcsModule implements Module
{
    private CurrentDirectory currentDirectory;

    public VcsModule(CurrentDirectory currentDirectory)
    {
        this.currentDirectory = currentDirectory;
    }

    public void configure(Binder binder)
    {
        if(currentDirectory.isDirectoryPresent(".git"))
        {
            binder.bind(Vcs.class).to(GitVcs.class);
        }
    }
}
