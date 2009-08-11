package org.autopair.commands;

public class UnknownCommandException extends RuntimeException
{
    public UnknownCommandException(String s)
    {
        super(s);
    }
}
