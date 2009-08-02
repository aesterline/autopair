package org.autopair.java;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import org.autopair.monitor.FileSystemChange;
import org.autopair.monitor.FileSystemChangeListener;

public class ProjectFileSystemMonitor implements FileSystemChangeListener
{
    private Project project;
    private ProjectFileFactory factory;

    @Inject
    public ProjectFileSystemMonitor(Project project, ProjectFileFactory factory)
    {
        this.project = project;
        this.factory = factory;
    }

    public void changes(List<FileSystemChange> changes)
    {
        List<ProjectFile> files = new ArrayList<ProjectFile>();

        for(FileSystemChange change : changes)
        {
            files.add(factory.create(change.getFile()));
        }

        project.changedFiles(files);
    }
}
