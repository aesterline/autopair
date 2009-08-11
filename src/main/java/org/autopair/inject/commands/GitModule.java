package org.autopair.inject.commands;

import com.google.inject.Binder;
import com.google.inject.Module;
import org.autopair.commands.Git;

public class GitModule implements Module
{
    public void configure(Binder binder)
    {
        binder.bind(Git.class).toProvider(GitProvider.class);
    }
}
