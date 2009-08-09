package org.autopair.inject.executable;

import com.google.inject.Binder;
import com.google.inject.matcher.Matcher;
import static com.google.inject.matcher.Matchers.any;
import static com.google.inject.matcher.Matchers.subclassesOf;
import org.aopalliance.intercept.MethodInterceptor;
import org.autopair.AutoPairConfig;
import org.autopair.exec.Shell;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class LogExecutableModuleTest
{
    private AutoPairConfig config;
    private Binder binder;
    private LogExecutableModule module;

    public void shouldBindInterceptorWhenLogging()
    {
        when(config.isLogging()).thenReturn(true);

        module.configure(binder);

        verify(binder).bindInterceptor(eq(subclassesOf(Shell.class)), eq(any()), (MethodInterceptor[]) anyObject());
    }

    public void shouldNotBindInterceptorWhenNotLogging()
    {
        when(config.isLogging()).thenReturn(false);

        module.configure(binder);

        verify(binder, never()).bindInterceptor((Matcher) anyObject(), (Matcher) anyObject(), (MethodInterceptor[]) anyObject());
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        config = mock(AutoPairConfig.class);
        binder = mock(Binder.class);
        module = new LogExecutableModule(config);
    }
}
