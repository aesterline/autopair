package org.autopair.monitor.spi.vcs;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class ClockTest
{
    private Clock clock;

    public void currentMarkShouldStartAtZero()
    {
        assertEquals(clock.currentMark(), 0);
    }

    public void markShouldAdvanceTheMark()
    {
        long initalMark = clock.currentMark();
        clock.mark();
        assertTrue(initalMark < clock.currentMark(), "mark should advance after a call to mark");
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        clock = new Clock();
    }
}
