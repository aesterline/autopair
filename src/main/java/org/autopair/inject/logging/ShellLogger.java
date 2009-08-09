package org.autopair.inject.logging;

import java.io.PrintStream;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;

public class ShellLogger implements MethodInterceptor
{
    private PrintStream printStream;

    public ShellLogger()
    {
        this(System.out);
    }

    public ShellLogger(PrintStream printStream)
    {
        this.printStream = printStream;
    }

    public Object invoke(MethodInvocation methodInvocation) throws Throwable
    {
        String[] command = (String[]) methodInvocation.getArguments()[0];
        printStream.printf("[shell]\t\t%s\n", StringUtils.join(command, " "));

        return methodInvocation.proceed();
    }
}
