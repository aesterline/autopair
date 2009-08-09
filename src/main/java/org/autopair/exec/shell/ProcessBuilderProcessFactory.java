package org.autopair.exec.shell;

import java.io.IOException;
import java.util.Map;

import org.autopair.exec.UnableToExecuteCommandException;

public class ProcessBuilderProcessFactory implements ProcessFactory
{
    private final Map<String, String> environment;

    public ProcessBuilderProcessFactory()
    {
        this(System.getenv());
    }

    public ProcessBuilderProcessFactory(Map<String, String> environment)
    {
        this.environment = environment;
    }

    public Process create(String... command)
    {
        return create(new ProcessBuilder(command));
    }

    public Process create(ProcessBuilder builder)
    {
        try
        {
            builder.redirectErrorStream(true);
            builder.environment().putAll(environment);

            return builder.start();
        }
        catch(IOException e)
        {
            throw new UnableToExecuteCommandException(e);
        }
    }
}
