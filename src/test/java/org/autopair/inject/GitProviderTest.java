package org.autopair.inject;

import com.google.inject.Provider;
import org.autopair.exec.Executable;
import org.autopair.exec.ExecutableFactory;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertSame;
import org.testng.annotations.Test;

@Test
public class GitProviderTest
{
    public void providerShouldCreateExecutableFromFactory()
    {
        ExecutableFactory factory = mock(ExecutableFactory.class);
        Executable executable = mock(Executable.class);
        when(factory.create(anyString())).thenReturn(executable);

        Provider<Executable> provider = new GitProvider(factory);
        Executable git = provider.get();

        assertSame(git, executable);
    }
}
