package org.agileide.exec;

public interface IExecutable
{
    IExecutable addArguments(String... arguments);
    String[] asArray();
}
