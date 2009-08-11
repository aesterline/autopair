package org.autopair.exec.executable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.autopair.exec.Executable;
import org.autopair.exec.Shell;

public class ShellExecutable implements Executable
{
    private List<String> command;
    private Shell shell;

    public ShellExecutable(Shell shell, List<String> command)
    {
        this.shell = shell;
        this.command = command;
    }

    public ShellExecutable(Shell shell, String... command)
    {
        this(shell, Arrays.asList(command));
    }

    public Executable addArguments(String... arguments)
    {
        List<String> newCommand = new ArrayList<String>(command);
        newCommand.addAll(Arrays.asList(arguments));

        return new ShellExecutable(shell, newCommand);
    }

    public String execute()
    {
        String[] executableCommand = command.toArray(new String[command.size()]);
        return shell.execute(executableCommand);
    }

    public String execute(String... arguments)
    {
        return addArguments(arguments).execute();
    }
}
