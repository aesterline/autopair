package org.autopair.inject.commands;

import com.google.inject.Provider;
import org.autopair.AutoPairConfig;
import org.autopair.commands.UnknownCommandException;
import org.autopair.exec.Executable;
import org.autopair.exec.ExecutableFactory;
import org.autopair.exec.UnableToExecuteCommandException;
import org.autopair.exec.executable.ShellExecutableFactory;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.fail;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class GitProviderTest
{
    private ExecutableFactory factory;
    private Executable executable;
    private Provider<Executable> provider;
    private AutoPairConfig config;

    public void shouldAskConfigForValue()
    {
        provider.get();

        verify(config).getValue(GitProvider.GIT_CONFIG_KEY, GitProvider.DEFAULT_GIT_PATH);
    }

    public void shouldCreateExecutable()
    {
        String gitPath = "/usr/git";
        when(config.getValue(anyString(), anyString())).thenReturn(gitPath);

        provider.get();

        verify(factory).create(gitPath);
    }

    public void shouldTestExecutable()
    {
        provider.get();

        verify(executable).execute(GitProvider.TEST_ARGUMENT);
    }

    public void shouldThrowExceptionWhenUnableToExecuteVersion()
    {
        when(executable.execute(GitProvider.TEST_ARGUMENT)).thenThrow(new UnableToExecuteCommandException());

        try
        {
            provider.get();
            fail();
        }
        catch(UnknownCommandException e)
        {
            // We expect this to happen.
        }
    }

    public void providerShouldCreateExecutableFromFactory()
    {
        assertSame(provider.get(), executable);
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        factory = mock(ShellExecutableFactory.class);
        executable = mock(Executable.class);
        config = mock(AutoPairConfig.class);

        when(factory.create(anyString())).thenReturn(executable);

        provider = new GitProvider(config, factory);
    }
}
