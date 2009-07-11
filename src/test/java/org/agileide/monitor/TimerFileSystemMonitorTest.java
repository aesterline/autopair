package org.agileide.monitor;

import java.util.Timer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

@Test
public class TimerFileSystemMonitorTest
{
    private FileSystemMonitorSpi spi;
    private Timer timer;
    private FileSystemChangeListener listener;

    public void listenersShouldBePassedToUnderlyingFileSystemMonitorSpi()
    {
        TimerFileSystemMonitor monitor = new TimerFileSystemMonitor(spi, timer, 10);
        monitor.setListener(listener);

        verify(spi).setListener(listener);
    }

    public void runShouldCheckForChanges()
    {
        TimerFileSystemMonitor monitor = new TimerFileSystemMonitor(spi, timer, 10);
        monitor.run();

        verify(spi).checkForChanges();
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
    }
}
