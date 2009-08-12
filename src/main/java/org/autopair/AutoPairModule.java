package org.autopair;

import java.io.File;
import java.util.concurrent.ScheduledExecutorService;

import com.google.inject.AbstractModule;
import org.autopair.inject.MavenProjectProvider;
import org.autopair.inject.ScheduledExecutorServiceProvider;
import org.autopair.inject.VcsChangeFilter;
import org.autopair.inject.VcsFileSystemChangeFilterProvider;
import org.autopair.inject.commands.GitModule;
import org.autopair.inject.executable.ExecutableModule;
import org.autopair.inject.executable.LogExecutableModule;
import org.autopair.inject.vcs.VcsModule;
import org.autopair.monitor.FileSystemChangeFilter;
import org.autopair.monitor.FileSystemChangeListener;
import org.autopair.monitor.FileSystemMonitor;
import org.autopair.monitor.FileSystemMonitorSpi;
import org.autopair.monitor.spi.vcs.VcsFileSystemMonitorSpi;
import org.autopair.monitor.timer.TimerFileSystemMonitor;

public class AutoPairModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        AutoPairConfig config = new AutoPairConfig();
        CurrentDirectory currentDirectory = new CurrentDirectory(new File("."));

        new ExecutableModule().configure(binder());
        new LogExecutableModule(config).configure(binder());
        new GitModule().configure(binder());
        new VcsModule(currentDirectory).configure(binder());

        bind(FileSystemChangeFilter.class).annotatedWith(VcsChangeFilter.class).toProvider(VcsFileSystemChangeFilterProvider.class);
        bind(FileSystemMonitorSpi.class).to(VcsFileSystemMonitorSpi.class);
        bind(ScheduledExecutorService.class).toProvider(ScheduledExecutorServiceProvider.class);
        bind(FileSystemMonitor.class).to(TimerFileSystemMonitor.class);

        bind(Project.class).toProvider(MavenProjectProvider.class);
        bind(FileSystemChangeListener.class).to(ProjectFileSystemChangeListener.class);
    }
}
