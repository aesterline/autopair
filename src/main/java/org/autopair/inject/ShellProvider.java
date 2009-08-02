package org.autopair.inject;

import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.autopair.exec.LoggingShell;
import org.autopair.exec.Shell;
import org.autopair.exec.shell.ProcessShell;

public class ShellProvider implements Provider<Shell>
{
    private ProcessShell shell;
    private Logger logger;

    @Inject
    public ShellProvider(ProcessShell shell, Logger logger)
    {
        this.shell = shell;
        this.logger = logger;
    }

    public Shell get()
    {
        return new LoggingShell(shell, logger);
    }
}
