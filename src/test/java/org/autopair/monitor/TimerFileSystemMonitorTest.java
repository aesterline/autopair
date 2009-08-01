package org.autopair.monitor;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class TimerFileSystemMonitorTest
{
    private FileSystemMonitorSpi spi;
    private Timer timer;
    private FileSystemChangeListener listener;
    private TimerFileSystemMonitor monitor;

    public void runShouldCheckForChanges()
    {
        monitor.run();

        verify(spi).checkForChanges();
    }

    public void listenersShouldBeNotifiedOfChanges()
    {
        List<FileSystemChange> changes = Arrays.asList(new FileSystemChange("junk.txt", SystemChangeType.ADDED));
        when(spi.checkForChanges()).thenReturn(changes);

        TimerFileSystemMonitor monitor = new TimerFileSystemMonitor(spi, timer, 10);
        monitor.setListener(listener);
        monitor.run();

        verify(listener).changes(changes);
    }

    public void monitorShouldBeScheduledWithSecondsTranslatedToMilliseconds()
    {
        TimerFileSystemMonitor monitor = new TimerFileSystemMonitor(spi, timer, 10);
        int millisInSecond = TimerFileSystemMonitor.NUMBER_OF_MILLIS_PER_SECOND;

        verify(timer).schedule(monitor, 10 * millisInSecond, 10 * millisInSecond);
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        spi = mock(FileSystemMonitorSpi.class);
        timer = mock(Timer.class);
        listener = mock(FileSystemChangeListener.class);
        monitor = new TimerFileSystemMonitor(spi, timer, 10);
        monitor.setListener(listener);
    }
}
