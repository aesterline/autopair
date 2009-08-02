package org.autopair.inject;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.autopair.exec.Executable;
import org.autopair.exec.ExecutableFactory;

public class GitProvider implements Provider<Executable>
{
    private ExecutableFactory factory;

    @Inject
    public GitProvider(ExecutableFactory factory)
    {
        this.factory = factory;
    }

    public Executable get()
    {
        return factory.create("/usr/local/git/bin/git");
    }
}
