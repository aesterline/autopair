package org.autopair.monitor.timer;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.inject.Inject;

public class ExecutionTimer
{
    private ScheduledExecutorService executorService;

    @Inject
    public ExecutionTimer(ScheduledExecutorService executorService)
    {
        this.executorService = executorService;
    }

    public void register(Runnable runnable)
    {
        executorService.scheduleWithFixedDelay(runnable, 10, 10, TimeUnit.SECONDS);
    }
}
