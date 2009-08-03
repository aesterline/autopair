package org.autopair;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.autopair.monitor.FileSystemChange;
import org.autopair.monitor.FileSystemChangeListener;
import org.autopair.monitor.SystemChangeType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class ProjectFileSystemChangeListenerTest
{
    private Project project;
    private FileSystemChangeListener listener;

    public void oneFileSystemChangeShouldBeConvertedToFile()
    {
        File changedFile = new File("MyJava.java");
        notifyListenerOfChanges(changedFile);

        verify(project).updates(Arrays.asList(changedFile));
    }

    public void multipleFileSystemChangeShouldBeConvertedToFiles()
    {
        File myJava = new File("MyJava.java");
        File myGroovy = new File("Groovy.java");
        notifyListenerOfChanges(myJava, myGroovy);

        verify(project).updates(Arrays.asList(myJava, myGroovy));
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        project = mock(Project.class);
        listener = new ProjectFileSystemChangeListener(project);
    }

    private void notifyListenerOfChanges(File... changedFile)
    {
        List<FileSystemChange> systemChanges = new ArrayList<FileSystemChange>();
        CollectionUtils.collect(
                Arrays.asList(changedFile),
                new Transformer()
                {

                    public Object transform(Object input)
                    {
                        return new FileSystemChange((File) input, SystemChangeType.MODIFIED);
                    }
                },
                systemChanges);

        listener.changes(systemChanges);
    }
}
