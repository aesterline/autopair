package org.agileide.monitor;

import java.util.Timer;
import java.util.TimerTask;

public class TimerFileSystemMonitor extends TimerTask implements FileSystemMonitor
{
    public static final int NUMBER_OF_MILLIS_PER_SECOND = 1000;

    private FileSystemMonitorSpi spi;

    public TimerFileSystemMonitor(FileSystemMonitorSpi spi, Timer timer, int delayInSeconds)
    {
        this.spi = spi;
        timer.schedule(this, delayInSeconds * NUMBER_OF_MILLIS_PER_SECOND, delayInSeconds * NUMBER_OF_MILLIS_PER_SECOND);
    }

    public void setListener(FileSystemChangeListener listener)
    {
        spi.setListener(listener);
    }

    public void run()
    {
        spi.checkForChanges();
    }
}
