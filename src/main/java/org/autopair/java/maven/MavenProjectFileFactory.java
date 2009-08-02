package org.autopair.java.maven;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.autopair.java.IgnoredFile;
import org.autopair.java.JavaFile;
import org.autopair.java.ProjectFile;
import org.autopair.java.ProjectFileFactory;

public class MavenProjectFileFactory implements ProjectFileFactory
{
    public ProjectFile create(File file)
    {
        String filename = file.getAbsolutePath();

        if(FilenameUtils.isExtension(filename, "java"))
        {
            return new JavaFile(file);
        }

        return new IgnoredFile();
    }
}
