package org.autopair;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.autopair.monitor.FileSystemChangeListener;
import org.autopair.monitor.FileSystemMonitor;

public class AutoPair
{
    private static final String CLASSPATH = "/Users/adam/.m2/repository/commons-io/commons-io/1.4/commons-io-1.4.jar:/Users/adam/.m2/repository/commons-lang/commons-lang/2.4/commons-lang-2.4.jar:/Users/adam/.m2/repository/commons-logging/commons-logging/1.1.1/commons-logging-1.1.1.jar:/Users/adam/.m2/repository/junit/junit/3.8.1/junit-3.8.1.jar:/Users/adam/.m2/repository/org/mockito/mockito-all/1.8.0-rc2/mockito-all-1.8.0-rc2.jar:/Users/adam/.m2/repository/org/testng/testng/5.9/testng-5.9-jdk15.jar";

    public static void main(String[] args)
    {
//        Log log = LogFactory.getLog(AutoPair.class);
//
//        Shell shell = new ProcessShell(new ProcessBuilderProcessFactory());
//        shell = new LoggingShell(shell, log);
//
//        ExecutableFactory executableFactory = new ExecutableFactory(shell);
//        Executable git = executableFactory.create("/usr/local/git/bin/git");
//        Executable javac = executableFactory.create("/System/Library/Frameworks/JavaVM.framework/Versions/1.6/Commands/javac");
//        Executable java = executableFactory.create("/System/Library/Frameworks/JavaVM.framework/Versions/1.6/Commands/java");
//
//        Executable mainCompiler = javac.addArguments(
//                "-classpath", CLASSPATH,
//                "-d", "/Users/adam/Projects/agileide.git/target/classes",
//                "-sourcepath", "/Users/adam/Projects/agileide.git/src/main/java");
//
//        Executable testCompiler = javac.addArguments(
//                "-classpath", CLASSPATH + ":/Users/adam/Projects/agileide.git/target/classes",
//                "-d", "/Users/adam/Projects/agileide.git/target/test-classes",
//                "-sourcepath", "/Users/adam/Projects/agileide.git/src/test/java");
//
//        Executable testngExe = java.addArguments("-cp", CLASSPATH + ":/Users/adam/Projects/agileide.git/target/classes:/Users/adam/Projects/agileide.git/target/test-classes",
//                                                 "org.testng.remote.RemoteTestNG",
//                                                 "-port", "5000");
//
//        new RemoteTestRunnerClient().start();
//
//        Vcs vcs = new GitVcs(new GitStatus(git));
//
//        RecentChangesFileSystemChangeFilter recentChangeFilter = new RecentChangesFileSystemChangeFilter(new Clock());
//        AddedChangesFileSystemChangeFilter addedChanges = new AddedChangesFileSystemChangeFilter();
//        FileSystemChangeFilterChain vcsFilters = new FileSystemChangeFilterChain(recentChangeFilter, addedChanges);
//
//        VcsFileSystemMonitorSpi spi = new VcsFileSystemMonitorSpi(vcs, vcsFilters);
//
//        Timer timer = new Timer();
//        FileSystemMonitor fileSystemMonitor = new TimerFileSystemMonitor(spi, timer, 10);
//
//        TestNg testng = new TestNg(testngExe, new SuiteXml());
//        FileToClassName ftc = new FileToClassName("/Users/adam/Projects/agileide.git/src/test/java");
//
//        JavacFileSystemListener javacListener = new JavacFileSystemListener(new Javac(mainCompiler), new Javac(testCompiler));
//        TestNGFileSystemChangeListener testNgListener = new TestNGFileSystemChangeListener(testng, ftc);
//        fileSystemMonitor.setListener(new FileSystemChangeListeners(javacListener, testNgListener));

        Injector injector = Guice.createInjector(new AutoPairModule());

        FileSystemMonitor monitor = injector.getInstance(FileSystemMonitor.class);
        FileSystemChangeListener listener = injector.getInstance(FileSystemChangeListener.class);

        monitor.start(listener);
    }
}
