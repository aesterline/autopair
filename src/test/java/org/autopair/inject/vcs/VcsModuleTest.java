package org.autopair.inject.vcs;

import com.google.inject.Binder;
import com.google.inject.binder.AnnotatedBindingBuilder;
import org.autopair.CurrentDirectory;
import org.autopair.monitor.spi.vcs.Vcs;
import org.autopair.monitor.spi.vcs.git.GitVcs;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class VcsModuleTest
{
    private CurrentDirectory currentDirectory;
    private VcsModule module;
    private AnnotatedBindingBuilder builder;
    private Binder binder;

    public void shouldBindGitVcsWhenGitPresentInCurrentDirectory()
    {
        when(currentDirectory.isDirectoryPresent(".git")).thenReturn(true);

        module.configure(binder);

        verify(binder).bind(Vcs.class);
        verify(builder).to(GitVcs.class);
    }

    public void shouldNotBindGitVcsWhenGitIsNotPresentInCurrentDirectory()
    {
        when(currentDirectory.isDirectoryPresent(".git")).thenReturn(false);

        module.configure(binder);

        verify(binder, never()).bind(Vcs.class);
        verify(builder, never()).to(GitVcs.class);
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        currentDirectory = mock(CurrentDirectory.class);
        module = new VcsModule(currentDirectory);
        builder = mock(AnnotatedBindingBuilder.class);
        binder = mock(Binder.class);
        when(binder.bind((Class) any())).thenReturn(builder);
    }
}
