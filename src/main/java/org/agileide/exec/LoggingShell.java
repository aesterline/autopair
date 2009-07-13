package org.agileide.exec;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;

public class LoggingShell implements Shell
{
    private Shell delegatee;
    private Log log;

    public LoggingShell(Shell delegatee, Log log)
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
