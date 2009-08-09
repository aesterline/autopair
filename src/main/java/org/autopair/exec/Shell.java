package org.autopair.exec;

public interface Shell
{
    String execute(String... command) throws UnableToExecuteCommandException;
}
