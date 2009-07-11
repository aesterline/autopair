package org.agileide.monitor;

import java.io.File;

import org.apache.commons.logging.Log;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

@Test
public class LoggingFileSystemChangeListenerTest
{
    private Log log;
    private LoggingFileSystemChangeListener listener;

    public void deletedFilesShouldBeLogged()
    {
        listener.deletedFile(new File("pom.xml"));

        verify(log).info("\tdeleted: pom.xml");
    }

    public void changedFilesShouldBeLogged()
    {
        listener.changedFile(new File("pom.xml"));

        verify(log).info("\tchanged: pom.xml");
    }

    public void newFilesShouldBeLogged()
    {
        listener.newFile(new File("pom.xml"));

        verify(log).info("\tnew: pom.xml");
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        log = mock(Log.class);
        listener = new LoggingFileSystemChangeListener(log);
    }
}
