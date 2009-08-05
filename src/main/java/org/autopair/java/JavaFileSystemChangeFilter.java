package org.autopair.java;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.io.FilenameUtils;
import org.autopair.monitor.FileSystemChange;
import org.autopair.monitor.FileSystemChangeFilter;

public class JavaFileSystemChangeFilter implements FileSystemChangeFilter
{
    private static final Predicate JAVA_FILE = new Predicate()
    {
        public boolean evaluate(Object object)
        {
            FileSystemChange change = (FileSystemChange)object;
            String filename = change.getFile().getAbsolutePath();
            return FilenameUtils.isExtension(filename, "java");
        }
    };

    public List<FileSystemChange> selectMatching(List<FileSystemChange> changes)
    {
        List<FileSystemChange> filtered = new ArrayList<FileSystemChange>();
        CollectionUtils.select(changes, JAVA_FILE, filtered);

        return filtered;
    }
}
