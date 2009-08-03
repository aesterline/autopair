package org.autopair.java.maven;

import java.io.File;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class MavenDeleteStrategyTest
{
    private Remove remove;
    private MavenCleanPhase phase;

    public void mainSrcShouldDeleteSameFileInTargetClasses()
    {
        File fileToDelete = createAbsoluteFileFor("autopair.git/src/main/java/org/autopair/junk/Junk.java");
        File expectedFileToDelete = createAbsoluteFileFor("autopair.git/target/classes/org/autopair/junk/Junk.class");

        phase.execute(Arrays.asList(fileToDelete));

        verify(remove).all(Arrays.asList(expectedFileToDelete));
    }

    public void mainTestShouldDeleteSameFileInTargetTestClasses()
    {
        File fileToDelete = createAbsoluteFileFor("autopair.git/src/test/java/org/autopair/junk/Junk.java");
        File expectedFileToDelete = createAbsoluteFileFor("autopair.git/target/test-classes/org/autopair/junk/Junk.class");

        phase.execute(Arrays.asList(fileToDelete));

        verify(remove).all(Arrays.asList(expectedFileToDelete));
    }

    private File createAbsoluteFileFor(String filename)
    {
        return new File(filename).getAbsoluteFile();
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        remove = mock(Remove.class);
        phase = new MavenCleanPhase(remove);
    }
}
