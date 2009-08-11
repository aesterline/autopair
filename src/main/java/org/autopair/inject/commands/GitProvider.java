package org.autopair.inject.commands;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.autopair.AutoPairConfig;
import org.autopair.commands.UnknownCommandException;
import org.autopair.exec.Executable;
import org.autopair.exec.ExecutableFactory;
import org.autopair.exec.UnableToExecuteCommandException;

public class GitProvider implements Provider<Executable>
{
    public static final String GIT_CONFIG_KEY = "exec.git";
    public static final String DEFAULT_GIT_PATH = "git";
    public static final String TEST_ARGUMENT = "--version";
    public static final String ERROR_TEMPLATE = "Unknown command: %s.   Please set %s in autopair's properties";

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
        String gitPath = config.getValue(GIT_CONFIG_KEY, DEFAULT_GIT_PATH);
        Executable git = factory.create(gitPath);
        test(git, gitPath);

        return git;
    }

    private void test(Executable git, String gitPath)
    {
        try
        {
            git.execute(TEST_ARGUMENT);
        }
        catch(UnableToExecuteCommandException e)
        {
            String errorMessage = String.format(ERROR_TEMPLATE, gitPath, GIT_CONFIG_KEY);
            throw new UnknownCommandException(errorMessage);
        }
    }
}
