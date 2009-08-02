package org.autopair.java.maven;

import java.io.File;

import org.autopair.java.DeleteStrategy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class MavenDeleteStrategyTest
{
    private DeleteStrategy delegatee;
    private MavenDeleteStrategy strategy;

    public void mainSrcShouldDeleteSameFileInTargetClasses()
    {
        File fileToDelete = createAbsoluteFileFor("autopair.git/src/main/org/autopair/junk/Junk.java");
        File expectedFileToDelete = createAbsoluteFileFor("autopair.git/target/classes/org/autopair/junk/Junk.java");

        strategy.delete(fileToDelete);

        verify(delegatee).delete(expectedFileToDelete);
    }

    public void mainTestShouldDeleteSameFileInTargetTestClasses()
    {
        File fileToDelete = createAbsoluteFileFor("autopair.git/src/test/org/autopair/junk/Junk.java");
        File expectedFileToDelete = createAbsoluteFileFor("autopair.git/target/test-classes/org/autopair/junk/Junk.java");

        strategy.delete(fileToDelete);

        verify(delegatee).delete(expectedFileToDelete);
    }

    private File createAbsoluteFileFor(String filename)
    {
        return new File(filename).getAbsoluteFile();
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        delegatee = mock(DeleteStrategy.class);
        strategy = new MavenDeleteStrategy(delegatee);
    }
}
