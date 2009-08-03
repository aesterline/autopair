package org.autopair;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.TransformerUtils;
import org.autopair.monitor.FileSystemChange;
import org.autopair.monitor.FileSystemChangeListener;

public class ProjectFileSystemChangeListener implements FileSystemChangeListener
{
    private Project project;
    private static final Transformer GET_FILE_TRANSFORMER = TransformerUtils.invokerTransformer("getFile");

    @Inject
    public ProjectFileSystemChangeListener(Project project)
    {
        this.project = project;
    }

    public void changes(List<FileSystemChange> changes)
    {
        List<File> updates = new ArrayList<File>();
        CollectionUtils.collect(changes, GET_FILE_TRANSFORMER, updates);

        project.updates(updates);
    }
}
