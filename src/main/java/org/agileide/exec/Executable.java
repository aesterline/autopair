package org.agileide.exec;

public interface Executable
{
    Executable addArguments(String... arguments);

    String execute();
}
