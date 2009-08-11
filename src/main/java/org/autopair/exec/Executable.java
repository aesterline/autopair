package org.autopair.exec;

public interface Executable
{
    Executable addArguments(String... arguments);

    String execute();

    String execute(String... arguments);
}
