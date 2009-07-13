package org.agileide;

import java.util.Timer;

import org.agileide.exec.Executable;
import org.agileide.exec.ExecutableFactory;
import org.agileide.exec.LoggingShell;
import org.agileide.exec.Shell;
import org.agileide.exec.shell.ProcessBuilderProcessFactory;
import org.agileide.exec.shell.ProcessShell;
import org.agileide.java.Javac;
import org.agileide.java.JavacFileSystemListener;
import org.agileide.monitor.FileSystemMonitor;
import org.agileide.monitor.FileSystemMonitorSpi;
import org.agileide.monitor.TimerFileSystemMonitor;
import org.agileide.monitor.git.GitFileSystemMonitor;
import org.agileide.monitor.git.GitStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AgileIDE
{
    private static final String CLASSPATH = "/Users/adam/.m2/repository/commons-io/commons-io/1.4/commons-io-1.4.jar:/Users/adam/.m2/repository/commons-lang/commons-lang/2.4/commons-lang-2.4.jar:/Users/adam/.m2/repository/commons-logging/commons-logging/1.1.1/commons-logging-1.1.1.jar:/Users/adam/.m2/repository/junit/junit/3.8.1/junit-3.8.1.jar:/Users/adam/.m2/repository/org/mockito/mockito-all/1.8.0-rc2/mockito-all-1.8.0-rc2.jar:/Users/adam/.m2/repository/org/testng/testng/5.9/testng-5.9-jdk15.jar";

    public static void main(String[] args)
    {
        Log log = LogFactory.getLog(AgileIDE.class);

        Shell shell = new ProcessShell(new ProcessBuilderProcessFactory());
        shell = new LoggingShell(shell, log);

        ExecutableFactory executableFactory = new ExecutableFactory(shell);
        Executable git = executableFactory.create("/usr/local/git/bin/git");
        Executable javac = executableFactory.create("/System/Library/Frameworks/JavaVM.framework/Versions/1.6/Commands/javac");
        Executable mainCompiler = javac.addArguments(
                "-classpath", CLASSPATH,
                "-d", "/Users/adam/Projects/agileide.git/target/classes",
                "-sourcepath", "/Users/adam/Projects/agileide.git/src/main/java");

        Executable testCompiler = javac.addArguments(
                "-classpath", CLASSPATH + ":/Users/adam/Projects/agileide.git/target/classes",
                "-d", "/Users/adam/Projects/agileide.git/target/test-classes",
                "-sourcepath", "/Users/adam/Projects/agileide.git/src/test/java");

        FileSystemMonitorSpi spi = new GitFileSystemMonitor(new GitStatus(git));
        Timer timer = new Timer();
        FileSystemMonitor fileSystemMonitor = new TimerFileSystemMonitor(spi, timer, 10);

        fileSystemMonitor.setListener(new JavacFileSystemListener(new Javac(mainCompiler), new Javac(testCompiler)));
    }
}
