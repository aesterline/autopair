package org.autopair.java;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;

import org.autopair.monitor.FileSystemChange;
import org.autopair.monitor.SystemChangeType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class ProjectFileSystemMonitorTest
{
    private Project project;
    private ProjectFileFactory factory;
    private ProjectFileSystemMonitor monitor;

    public void emptyChangeListShouldRelayEmptyProjectFilesToProject()
    {
        monitor.changes(Collections.<FileSystemChange>emptyList());

        verify(project).changedFiles(Collections.<ProjectFile>emptyList());
    }

    public void oneChangeShouldRelayFactoryCreatedProjectFileToProject()
    {
        File file = new File("whocares");
        FileSystemChange change = new FileSystemChange(file, SystemChangeType.DELETED);
        ProjectFile projectFile = mockAndCreateProjectFileFor(file);

        monitor.changes(Collections.singletonList(change));

        verify(project).changedFiles(Collections.singletonList(projectFile));
    }

    public void multipleChangesShouldRelayAllProjectFilesToProject()
    {
        File file = new File("whocares");
        FileSystemChange change = new FileSystemChange(file, SystemChangeType.DELETED);
        ProjectFile projectFile = mockAndCreateProjectFileFor(file);

        monitor.changes(Arrays.asList(change, change));

        verify(project).changedFiles(Arrays.asList(projectFile, projectFile));
    }

    private ProjectFile mockAndCreateProjectFileFor(File file)
    {
        ProjectFile projectFile = mock(ProjectFile.class);
        when(factory.create(file)).thenReturn(projectFile);
        return projectFile;
    }

    @BeforeMethod
    protected void setUp() throws Exception
    {
        project = mock(Project.class);
        factory = mock(ProjectFileFactory.class);
        monitor = new ProjectFileSystemMonitor(project, factory);
    }
}
