package org.autopair.monitor.timer;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.testng.annotations.Test;

@Test
public class ExecutionTimerTest
{
    public void delayShouldRegisterWithScheduledExecutorService()
    {
        ScheduledExecutorService executorService = mock(ScheduledExecutorService.class);
        ExecutionTimer timer = new ExecutionTimer(executorService);
        Runnable runnable = new Runnable()
        {
            public void run() {}
        };

        timer.register(runnable);

        verify(executorService).scheduleWithFixedDelay(runnable, 10, 10, TimeUnit.SECONDS);
    }
}
