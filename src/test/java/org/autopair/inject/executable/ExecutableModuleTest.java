package org.autopair.inject.executable;

import com.google.inject.Binder;
import com.google.inject.binder.AnnotatedBindingBuilder;
import org.autopair.exec.ExecutableFactory;
import org.autopair.exec.Shell;
import org.autopair.exec.executable.ShellExecutableFactory;
import org.autopair.exec.shell.ProcessBuilderProcessFactory;
import org.autopair.exec.shell.ProcessFactory;
import org.autopair.exec.shell.ProcessShell;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class ExecutableModuleTest
{
    private Binder binder;
    private AnnotatedBindingBuilder builder;
    private ExecutableModule module;

    public void processFactoryShouldBeBound()
    {
        module.configure(binder);

        verify(binder).bind(ProcessFactory.class);
        verify(builder).to(ProcessBuilderProcessFactory.class);
    }

    public void shellShouldBeBound()
    {
        module.configure(binder);

        verify(binder).bind(Shell.class);
        verify(builder).to(ProcessShell.class);
    }

    public void executableFactoryShouldBeBound()
    {
        module.configure(binder);

        verify(binder).bind(ExecutableFactory.class);
        verify(builder).to(ShellExecutableFactory.class);
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        builder = mock(AnnotatedBindingBuilder.class);

        binder = mock(Binder.class);
        when(binder.bind((Class) any())).thenReturn(builder);

        module = new ExecutableModule();
    }
}
