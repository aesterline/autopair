package org.agileide.exec;

public interface Shell
{
    String execute(String... command);

    String execute(IExecutable status);
}
