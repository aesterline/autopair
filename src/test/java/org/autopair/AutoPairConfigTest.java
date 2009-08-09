package org.autopair;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

@Test
public class AutoPairConfigTest
{
    public void loggingShouldBeTurnedOnBeDefault()
    {
        assertTrue(new AutoPairConfig().isLogging());
    }

    public void pathForGitShouldBeShortNameByDefault()
    {
        assertEquals(new AutoPairConfig().getPathForGit(), "git");
    }
}
