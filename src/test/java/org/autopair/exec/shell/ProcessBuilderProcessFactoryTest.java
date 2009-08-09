package org.autopair.exec.shell;

import java.util.Collections;
import java.util.Map;

import org.apache.commons.lang.SystemUtils;
import org.autopair.exec.UnableToExecuteCommandException;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class ProcessBuilderProcessFactoryTest
{
    private static String[] TEST_COMMAND = {
            String.format("%s/bin/java", SystemUtils.JAVA_HOME),
            "-version"
    };
    private ProcessBuilder builder;

    public void shouldRedirectStream()
    {
        ProcessBuilderProcessFactory factory = new ProcessBuilderProcessFactory();

        factory.create(builder);

        assertTrue(builder.redirectErrorStream(), "Error stream should be redirected");
    }

    public void shouldAddAllEnvironmentVariables()
    {
        Map<String, String> environment = Collections.singletonMap("ME", "YOU");
        ProcessBuilderProcessFactory factory = new ProcessBuilderProcessFactory(environment);

        factory.create(builder);

        assertEquals(builder.environment().get("ME"), "YOU");
    }

    public void unknownCommandShouldThrowException()
    {
        builder = new ProcessBuilder("unknownCommand");
        ProcessBuilderProcessFactory factory = new ProcessBuilderProcessFactory();

        try
        {
            factory.create(builder);
            fail("Unknown commands should throw exception");
        }
        catch(UnableToExecuteCommandException e)
        {
            // we expect this to happen
        }
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        builder = new ProcessBuilder(TEST_COMMAND);
    }
}
