package org.autopair.exec.shell;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.autopair.exec.Shell;

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
}
