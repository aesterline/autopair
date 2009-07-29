package org.autopair.java;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cleaner
{
    private List<File> files;
    private DeleteStrategy delete;

    public Cleaner(DeleteStrategy delete)
    {
        this.delete = delete;
        this.files = new ArrayList<File>();
    }

    public void clean()
    {
        for(File file : files) delete.delete(file);
    }

    public void addDirtyFiles(File... files)
    {
        this.files.addAll(Arrays.asList(files));
    }
}
