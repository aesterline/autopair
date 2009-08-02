package org.autopair.monitor;

import java.util.Timer;
import java.util.TimerTask;

import com.google.inject.Inject;

public class TimerFileSystemMonitor extends TimerTask implements FileSystemMonitor
{
    public static final int NUMBER_OF_MILLIS_PER_SECOND = 1000;

    private FileSystemMonitorSpi spi;
    private FileSystemChangeListener listener;

    @Inject
    public TimerFileSystemMonitor(FileSystemMonitorSpi spi)
    {
        this(spi, new Timer(), 10);
    }

    public TimerFileSystemMonitor(FileSystemMonitorSpi spi, Timer timer, int delayInSeconds)
    {
        this.spi = spi;
        timer.schedule(this, delayInSeconds * NUMBER_OF_MILLIS_PER_SECOND, delayInSeconds * NUMBER_OF_MILLIS_PER_SECOND);
    }

    public void setListener(FileSystemChangeListener listener)
    {
        this.listener = listener;
    }

    public void run()
    {
        listener.changes(spi.checkForChanges());
    }
}
