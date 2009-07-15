package org.autopair.exec.shell;

public interface ProcessFactory
{
    Process create(String... command);
}
