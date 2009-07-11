package org.agileide.exec.shell;

public interface ProcessFactory
{
    Process create(String... command);
}
