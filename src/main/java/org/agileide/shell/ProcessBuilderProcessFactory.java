package org.agileide.shell;

import java.io.IOException;

public class ProcessBuilderProcessFactory implements ProcessFactory
{
    public Process create(String... command)
    {
        try
        {
            ProcessBuilder builder = new ProcessBuilder(command);
            builder.redirectErrorStream(true);

            return builder.start();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
