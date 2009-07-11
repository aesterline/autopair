package org.agileide.exec;

import java.io.File;

public class ExecutableFactory
{
    public static String DOES_NOT_EXIST_MESSAGE = "%s does not exist";
    public static String CANNOT_EXECUTE = "%s cannot be executed";

    public IExecutable create(File executable)
    {
        if(executable.exists() == false)
        {
            throw new InvalidedExecutableException(String.format(DOES_NOT_EXIST_MESSAGE, executable.getAbsolutePath()));
        }
        if(executable.canExecute() == false)
        {
            throw new InvalidedExecutableException(String.format(CANNOT_EXECUTE, executable.getAbsolutePath()));
        }

        return new DefaultExecutable(executable.getAbsolutePath());
    }
}
