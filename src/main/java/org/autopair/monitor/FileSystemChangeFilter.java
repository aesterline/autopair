package org.autopair.monitor;

import java.util.List;

public interface FileSystemChangeFilter
{
    public List<FileSystemChange> selectMatching(List<FileSystemChange> changes);
}
