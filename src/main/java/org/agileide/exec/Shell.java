package org.agileide.exec;

public interface Shell
{
    String execute(String... command);

    String execute(Executable status);
}
