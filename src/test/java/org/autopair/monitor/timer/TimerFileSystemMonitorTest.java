package org.autopair.monitor.timer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.autopair.monitor.FileSystemChange;
import org.autopair.monitor.FileSystemChangeListener;
import org.autopair.monitor.FileSystemMonitorSpi;
import org.autopair.monitor.SystemChangeType;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class TimerFileSystemMonitorTest
{
    private FileSystemMonitorSpi spi;
    private FileSystemChangeListener listener;
    private ExecutionTimer timer;
    private TimerFileSystemMonitor monitor;

    public void startShouldRegisterWithTimer()
    {
        monitor.start(listener);

        verify(timer).register((Runnable) anyObject());
    }

    public void executionOfTimerShouldNotifyListenerOfChanges()
    {
        List<FileSystemChange> changes = Arrays.asList(new FileSystemChange("junk.txt", SystemChangeType.ADDED));
        when(spi.checkForChanges()).thenReturn(changes);
        doAnswer(new InvokeRunnable()).when(timer).register((Runnable) anyObject());

        monitor.start(listener);

        verify(listener).changes(changes);
    }

    public void emptyChangeListShouldNotNotifyListenerOfChanges()
    {
        List<FileSystemChange> changes = Collections.emptyList();
        when(spi.checkForChanges()).thenReturn(changes);
        doAnswer(new InvokeRunnable()).when(timer).register((Runnable) anyObject());

        monitor.start(listener);

        verify(listener, never()).changes(changes);        
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        spi = mock(FileSystemMonitorSpi.class);
        listener = mock(FileSystemChangeListener.class);
        timer = mock(ExecutionTimer.class);
        monitor = new TimerFileSystemMonitor(spi, timer);
    }

    private static class InvokeRunnable implements Answer
    {
        public Object answer(InvocationOnMock invocation) throws Throwable
        {
            Runnable runnable = (Runnable) invocation.getArguments()[0];
            runnable.run();
            return null;
        }
    }
}
