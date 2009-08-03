package org.autopair;

import com.google.inject.AbstractModule;
import org.autopair.exec.Executable;
import org.autopair.exec.Shell;
import org.autopair.exec.shell.ProcessBuilderProcessFactory;
import org.autopair.exec.shell.ProcessFactory;
import org.autopair.inject.Git;
import org.autopair.inject.GitProvider;
import org.autopair.inject.MavenProjectProvider;
import org.autopair.inject.ShellProvider;
import org.autopair.inject.VcsChangeFilter;
import org.autopair.inject.VcsFileSystemChangeFilterProvider;
import org.autopair.monitor.FileSystemChangeFilter;
import org.autopair.monitor.FileSystemChangeListener;
import org.autopair.monitor.FileSystemMonitor;
import org.autopair.monitor.FileSystemMonitorSpi;
import org.autopair.monitor.TimerFileSystemMonitor;
import org.autopair.monitor.vcs.Vcs;
import org.autopair.monitor.vcs.VcsFileSystemMonitorSpi;
import org.autopair.monitor.vcs.git.GitVcs;

public class AutoPairModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(ProcessFactory.class).to(ProcessBuilderProcessFactory.class);
        bind(Shell.class).toProvider(ShellProvider.class);

        bind(Executable.class).annotatedWith(Git.class).toProvider(GitProvider.class);
        bind(Vcs.class).to(GitVcs.class);

        bind(FileSystemChangeFilter.class).annotatedWith(VcsChangeFilter.class).toProvider(VcsFileSystemChangeFilterProvider.class);
        bind(FileSystemMonitorSpi.class).to(VcsFileSystemMonitorSpi.class);
        bind(FileSystemMonitor.class).to(TimerFileSystemMonitor.class);

        bind(Project.class).toProvider(MavenProjectProvider.class);
        bind(FileSystemChangeListener.class).to(ProjectFileSystemChangeListener.class);
    }
}
