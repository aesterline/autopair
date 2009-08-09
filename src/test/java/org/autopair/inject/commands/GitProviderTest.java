package org.autopair.inject.commands;

import com.google.inject.Provider;
import org.autopair.AutoPairConfig;
import org.autopair.exec.Executable;
import org.autopair.exec.ExecutableFactory;
import org.autopair.exec.executable.ShellExecutableFactory;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertSame;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class GitProviderTest
{
    private ExecutableFactory factory;
    private Executable executable;
    private Provider<Executable> provider;
    private AutoPairConfig config;

    public void providerShouldUseConfigurationToCreateGit()
    {
        String pathForGit = "/some/path/git";
        when(config.getPathForGit()).thenReturn(pathForGit);

        provider.get();

        verify(factory).create(pathForGit);
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
