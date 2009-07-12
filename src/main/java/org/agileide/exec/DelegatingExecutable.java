package org.agileide.exec;

public class DelegatingExecutable implements Executable
{
    private Executable delegatee;

    public DelegatingExecutable(Executable delegatee)
    {
        this.delegatee = delegatee;
    }

    public Executable addArguments(String... arguments) {return delegatee.addArguments(arguments);}

    public String execute() {return delegatee.execute();}
}
