package org.autopair.monitor.timer;

import com.google.inject.Inject;
import org.autopair.monitor.FileSystemChangeListener;
import org.autopair.monitor.FileSystemMonitor;
import org.autopair.monitor.FileSystemMonitorSpi;

public class TimerFileSystemMonitor implements FileSystemMonitor
{
    public static final int NUMBER_OF_MILLIS_PER_SECOND = 1000;

    private FileSystemMonitorSpi spi;
    private ExecutionTimer timer;

    @Inject
    public TimerFileSystemMonitor(FileSystemMonitorSpi spi, ExecutionTimer timer)
    {
        this.spi = spi;
        this.timer = timer;
    }

    public void start(final FileSystemChangeListener listener)
    {
        timer.register(new Runnable()
        {
            public void run()
            {
                listener.changes(spi.checkForChanges());
            }
        });
    }
}
