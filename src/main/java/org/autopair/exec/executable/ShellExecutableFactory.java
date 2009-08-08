package org.autopair.exec.executable;

import com.google.inject.Inject;
import org.autopair.exec.Executable;
import org.autopair.exec.ExecutableFactory;
import org.autopair.exec.Shell;

public class ShellExecutableFactory implements ExecutableFactory
{
    private Shell shell;

    @Inject
    public ShellExecutableFactory(Shell shell)
    {
        this.shell = shell;
    }

    public Executable create(String command)
    {
        return new ShellExecutable(shell, command);
    }
}
