package org.autopair;

import java.io.File;
import java.util.List;

public interface Phase
{
    void execute(List<File> updates);
}
