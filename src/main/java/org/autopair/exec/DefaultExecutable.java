package org.autopair.exec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultExecutable implements Executable
{
    private List<String> command;
    private Shell shell;

    public DefaultExecutable(Shell shell, List<String> command)
    {
        this.shell = shell;
        this.command = command;
    }

    public DefaultExecutable(Shell shell, String... command)
    {
        this(shell, Arrays.asList(command));
    }

    public Executable addArguments(String... arguments)
    {
        List<String> newCommand = new ArrayList<String>(command);
        newCommand.addAll(Arrays.asList(arguments));

        return new DefaultExecutable(shell, newCommand);
    }

    public String execute()
    {
        String[] executableCommand = command.toArray(new String[command.size()]);
        return shell.execute(executableCommand);
    }
}
