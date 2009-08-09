package org.autopair.exec;

public class UnableToExecuteCommandException extends RuntimeException
{
    public UnableToExecuteCommandException(Exception e)
    {
        super(e);
    }
}
