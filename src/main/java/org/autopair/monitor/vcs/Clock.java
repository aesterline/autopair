package org.autopair.monitor.vcs;

public class Clock
{
    private long currentMark = 0;

    public long currentMark()
    {
        return currentMark;
    }

    public void mark()
    {
        currentMark = System.currentTimeMillis();
    }
}
