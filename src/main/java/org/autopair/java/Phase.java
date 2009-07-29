package org.autopair.java;

import java.util.List;

public interface Phase
{
    void execute(List<ProjectFile> files);
}
