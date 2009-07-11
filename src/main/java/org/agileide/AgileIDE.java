package org.agileide;

import java.util.Timer;

import org.agileide.exec.Executable;
import org.agileide.exec.ExecutableFactory;
import org.agileide.exec.Shell;
import org.agileide.exec.shell.ProcessBuilderProcessFactory;
import org.agileide.exec.shell.ProcessShell;
import org.agileide.monitor.FileSystemMonitor;
import org.agileide.monitor.FileSystemMonitorSpi;
import org.agileide.monitor.LoggingFileSystemChangeListener;
import org.agileide.monitor.TimerFileSystemMonitor;
import org.agileide.monitor.git.Git;
import org.agileide.monitor.git.GitFileSystemMonitor;
import org.agileide.monitor.git.GitStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AgileIDE
{
    public static void main(String[] args)
    {
        Shell shell = new ProcessShell(new ProcessBuilderProcessFactory());
        Log log = LogFactory.getLog(LoggingFileSystemChangeListener.class);
        ExecutableFactory factory = new ExecutableFactory();
        Executable git = factory.create("/usr/local/git/bin/git");

        FileSystemMonitorSpi spi = new GitFileSystemMonitor(new Git(new GitStatus(git), shell));
        Timer timer = new Timer();

        FileSystemMonitor fileSystemMonitor = new TimerFileSystemMonitor(spi, timer, 10);
        fileSystemMonitor.setListener(new LoggingFileSystemChangeListener(log));
    }
}
