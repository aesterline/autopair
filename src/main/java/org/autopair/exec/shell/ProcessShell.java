package org.autopair.exec.shell;

import com.google.inject.Inject;
import org.apache.commons.io.IOUtils;
import org.autopair.exec.Shell;
import org.autopair.exec.UnableToExecuteCommandException;

public class ProcessShell implements Shell
{
    private ProcessFactory factory;

    @Inject
    public ProcessShell(ProcessFactory factory)
    {
        this.factory = factory;
    }

    public String execute(String... command) throws UnableToExecuteCommandException
    {
        try
        {
            Process process = factory.create(command);
            return IOUtils.toString(process.getInputStream());
        }
        catch(Exception e)
        {
            throw new UnableToExecuteCommandException(e);
        }
    }
}
