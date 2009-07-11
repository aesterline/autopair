package org.agileide.shell;

public interface ProcessFactory
{
    Process create(String... command);
}
