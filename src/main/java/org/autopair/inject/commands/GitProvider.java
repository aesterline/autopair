package org.autopair.inject.commands;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.autopair.AutoPairConfig;
import org.autopair.exec.Executable;
import org.autopair.exec.ExecutableFactory;

public class GitProvider implements Provider<Executable>
{
    private AutoPairConfig config;
    private ExecutableFactory factory;

    @Inject
    public GitProvider(AutoPairConfig config, ExecutableFactory factory)
    {
        this.config = config;
        this.factory = factory;
    }

    public Executable get()
    {
        return factory.create(config.getPathForGit());
    }
}
