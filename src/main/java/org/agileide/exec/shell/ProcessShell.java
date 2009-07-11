package org.agileide.exec.shell;

import java.io.IOException;

import org.agileide.exec.Executable;
import org.agileide.exec.Shell;
import org.apache.commons.io.IOUtils;

public class ProcessShell implements Shell
{
    private ProcessFactory factory;

    public ProcessShell(ProcessFactory factory)
    {
        this.factory = factory;
    }

    public String execute(String... command)
    {
        try
        {
            Process process = factory.create(command);
            return IOUtils.toString(process.getInputStream());
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public String execute(Executable executable)
    {
        return execute(executable.asArray());
    }
}
