package org.autopair.inject.executable;

import com.google.inject.Binder;
import com.google.inject.Module;
import org.autopair.exec.ExecutableFactory;
import org.autopair.exec.Shell;
import org.autopair.exec.executable.ShellExecutableFactory;
import org.autopair.exec.shell.ProcessBuilderProcessFactory;
import org.autopair.exec.shell.ProcessFactory;
import org.autopair.exec.shell.ProcessShell;

public class ExecutableModule implements Module
{
    public void configure(Binder binder)
    {
        binder.bind(ProcessFactory.class).to(ProcessBuilderProcessFactory.class);
        binder.bind(Shell.class).to(ProcessShell.class);
        binder.bind(ExecutableFactory.class).to(ShellExecutableFactory.class);
    }
}
