package org.autopair;

import java.util.concurrent.ScheduledExecutorService;

import com.google.inject.AbstractModule;
import org.autopair.exec.Executable;
import org.autopair.inject.Git;
import org.autopair.inject.GitProvider;
import org.autopair.inject.MavenProjectProvider;
import org.autopair.inject.ScheduledExecutorServiceProvider;
import org.autopair.inject.VcsChangeFilter;
import org.autopair.inject.VcsFileSystemChangeFilterProvider;
import org.autopair.inject.executable.ExecutableModule;
import org.autopair.inject.executable.LogExecutableModule;
import org.autopair.monitor.FileSystemChangeFilter;
import org.autopair.monitor.FileSystemChangeListener;
import org.autopair.monitor.FileSystemMonitor;
import org.autopair.monitor.FileSystemMonitorSpi;
import org.autopair.monitor.spi.vcs.Vcs;
import org.autopair.monitor.spi.vcs.VcsFileSystemMonitorSpi;
import org.autopair.monitor.spi.vcs.git.GitVcs;
import org.autopair.monitor.timer.TimerFileSystemMonitor;

public class AutoPairModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        AutoPairConfig config = new AutoPairConfig();

        new ExecutableModule().configure(binder());
        new LogExecutableModule(config).configure(binder());

        bind(Executable.class).annotatedWith(Git.class).toProvider(GitProvider.class);
        bind(Vcs.class).to(GitVcs.class);

        bind(FileSystemChangeFilter.class).annotatedWith(VcsChangeFilter.class).toProvider(VcsFileSystemChangeFilterProvider.class);
        bind(FileSystemMonitorSpi.class).to(VcsFileSystemMonitorSpi.class);
        bind(ScheduledExecutorService.class).toProvider(ScheduledExecutorServiceProvider.class);
        bind(FileSystemMonitor.class).to(TimerFileSystemMonitor.class);

        bind(Project.class).toProvider(MavenProjectProvider.class);
        bind(FileSystemChangeListener.class).to(ProjectFileSystemChangeListener.class);
    }
}
