package org.agileide.exec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultExecutable implements Executable
{
    private List<String> command;

    public DefaultExecutable(String... command)
    {
        this.command = Arrays.asList(command);
    }

    public DefaultExecutable(List<String> command)
    {
        this.command = command;
    }

    public Executable addArguments(String... arguments)
    {
        List<String> newCommand = new ArrayList<String>(command);
        newCommand.addAll(Arrays.asList(arguments));

        return new DefaultExecutable(newCommand);
    }

    public String[] asArray()
    {
        return command.toArray(new String[command.size()]);
    }
}
