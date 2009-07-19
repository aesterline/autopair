package org.autopair;

import java.util.Timer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.autopair.exec.Executable;
import org.autopair.exec.ExecutableFactory;
import org.autopair.exec.LoggingShell;
import org.autopair.exec.Shell;
import org.autopair.exec.shell.ProcessBuilderProcessFactory;
import org.autopair.exec.shell.ProcessShell;
import org.autopair.java.Javac;
import org.autopair.java.JavacFileSystemListener;
import org.autopair.java.testng.FileToClassName;
import org.autopair.java.testng.RemoteTestRunnerClient;
import org.autopair.java.testng.SuiteXml;
import org.autopair.java.testng.TestNGFileSystemChangeListener;
import org.autopair.java.testng.TestNg;
import org.autopair.monitor.FileSystemChangeFilterChain;
import org.autopair.monitor.FileSystemChangeListeners;
import org.autopair.monitor.FileSystemMonitor;
import org.autopair.monitor.TimerFileSystemMonitor;
import org.autopair.monitor.vcs.AddedChangesFileSystemChangeFilter;
import org.autopair.monitor.vcs.Clock;
import org.autopair.monitor.vcs.RecentChangesFileSystemChangeFilter;
import org.autopair.monitor.vcs.Vcs;
import org.autopair.monitor.vcs.VcsFileSystemMonitorSpi;
import org.autopair.monitor.vcs.git.GitStatus;
import org.autopair.monitor.vcs.git.GitVcs;

public class AutoPair
{
    private static final String CLASSPATH = "/Users/adam/.m2/repository/commons-io/commons-io/1.4/commons-io-1.4.jar:/Users/adam/.m2/repository/commons-lang/commons-lang/2.4/commons-lang-2.4.jar:/Users/adam/.m2/repository/commons-logging/commons-logging/1.1.1/commons-logging-1.1.1.jar:/Users/adam/.m2/repository/junit/junit/3.8.1/junit-3.8.1.jar:/Users/adam/.m2/repository/org/mockito/mockito-all/1.8.0-rc2/mockito-all-1.8.0-rc2.jar:/Users/adam/.m2/repository/org/testng/testng/5.9/testng-5.9-jdk15.jar";

    public static void main(String[] args)
    {
        Log log = LogFactory.getLog(AutoPair.class);

        Shell shell = new ProcessShell(new ProcessBuilderProcessFactory());
        shell = new LoggingShell(shell, log);

        ExecutableFactory executableFactory = new ExecutableFactory(shell);
        Executable git = executableFactory.create("/usr/local/git/bin/git");
        Executable javac = executableFactory.create("/System/Library/Frameworks/JavaVM.framework/Versions/1.6/Commands/javac");
        Executable java = executableFactory.create("/System/Library/Frameworks/JavaVM.framework/Versions/1.6/Commands/java");

        Executable mainCompiler = javac.addArguments(
                "-classpath", CLASSPATH,
                "-d", "/Users/adam/Projects/agileide.git/target/classes",
                "-sourcepath", "/Users/adam/Projects/agileide.git/src/main/java");

        Executable testCompiler = javac.addArguments(
                "-classpath", CLASSPATH + ":/Users/adam/Projects/agileide.git/target/classes",
                "-d", "/Users/adam/Projects/agileide.git/target/test-classes",
                "-sourcepath", "/Users/adam/Projects/agileide.git/src/test/java");

        Executable testngExe = java.addArguments("-cp", CLASSPATH + ":/Users/adam/Projects/agileide.git/target/classes:/Users/adam/Projects/agileide.git/target/test-classes",
                                                 "org.testng.remote.RemoteTestNG",
                                                 "-port", "5000");

        new RemoteTestRunnerClient().start();

        Vcs vcs = new GitVcs(new GitStatus(git));

        RecentChangesFileSystemChangeFilter recentChangeFilter = new RecentChangesFileSystemChangeFilter(new Clock());
        AddedChangesFileSystemChangeFilter addedChanges = new AddedChangesFileSystemChangeFilter();
        FileSystemChangeFilterChain vcsFilters = new FileSystemChangeFilterChain(recentChangeFilter, addedChanges);

        VcsFileSystemMonitorSpi spi = new VcsFileSystemMonitorSpi(vcs, vcsFilters);

        Timer timer = new Timer();
        FileSystemMonitor fileSystemMonitor = new TimerFileSystemMonitor(spi, timer, 10);

        TestNg testng = new TestNg(testngExe, new SuiteXml());
        FileToClassName ftc = new FileToClassName("/Users/adam/Projects/agileide.git/src/test/java");

        JavacFileSystemListener javacListener = new JavacFileSystemListener(new Javac(mainCompiler), new Javac(testCompiler));
        TestNGFileSystemChangeListener testNgListener = new TestNGFileSystemChangeListener(testng, ftc);
        fileSystemMonitor.setListener(new FileSystemChangeListeners(javacListener, testNgListener));
    }
}
