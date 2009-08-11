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

    public void unknownKeyShouldReturnDefault()
    {
        String defaultValue = "default";
        String value = new AutoPairConfig().getValue("unknownKey", defaultValue);

        assertEquals(value, defaultValue);
    }
}
