package org.autopair.exec;

public class UnableToExecuteCommandException extends RuntimeException
{
    public UnableToExecuteCommandException()
    {
    }

    public UnableToExecuteCommandException(Exception e)
    {
        super(e);
    }
}
