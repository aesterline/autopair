package org.autopair.inject;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.google.inject.Provider;

public class ScheduledExecutorServiceProvider implements Provider<ScheduledExecutorService>
{
    public ScheduledExecutorService get()
    {
        return Executors.newSingleThreadScheduledExecutor();
    }
}
