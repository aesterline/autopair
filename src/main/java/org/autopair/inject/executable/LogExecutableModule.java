package org.autopair.inject.executable;

import com.google.inject.Binder;
import com.google.inject.Module;
import static com.google.inject.matcher.Matchers.any;
import static com.google.inject.matcher.Matchers.subclassesOf;
import org.autopair.AutoPairConfig;
import org.autopair.exec.Shell;
import org.autopair.inject.logging.ShellLogger;

public class LogExecutableModule implements Module
{
    private AutoPairConfig config;

    public LogExecutableModule(AutoPairConfig config)
    {
        this.config = config;
    }

    public void configure(Binder binder)
    {
        if(config.isLogging())
        {
            binder.bindInterceptor(subclassesOf(Shell.class), any(), new ShellLogger());
        }
    }
}
