package org.agileide.exec;

public class DelegatingExecutable implements IExecutable
{
    private IExecutable delegatee;

    public DelegatingExecutable(IExecutable delegatee)
    {
        this.delegatee = delegatee;
    }

    public IExecutable addArguments(String... arguments) {return delegatee.addArguments(arguments);}

    public String[] asArray() {return delegatee.asArray();}
}
