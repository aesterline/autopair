package org.autopair.inject.commands;

import com.google.inject.Binder;
import com.google.inject.Module;
import org.autopair.exec.Executable;
import org.autopair.exec.annotations.Git;

public class GitModule implements Module
{
    public void configure(Binder binder)
    {
        binder.bind(Executable.class).annotatedWith(Git.class).toProvider(GitProvider.class);
    }
}
