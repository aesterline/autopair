package org.autopair.exec;

import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

public class LoggingShell implements Shell
{
    private Shell delegatee;
    private Logger log;

    public LoggingShell(Shell delegatee, Logger log)
    {
        this.delegatee = delegatee;
        this.log = log;
    }

    public String execute(String... command)
    {
        String readableCommand = StringUtils.join(command, ' ');
        log.info("executing: " + readableCommand);

        String result = delegatee.execute(command);
        log.info(String.format("%s\n\t%s", readableCommand, result));

        return result;
    }
}
