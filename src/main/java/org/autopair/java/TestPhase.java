package org.autopair.java;

import java.util.List;

import org.autopair.Tester;

public class TestPhase implements Phase
{
    private Tester tester;

    public TestPhase(Tester tester)
    {
        this.tester = tester;
    }

    public void execute(List<ProjectFile> files)
    {
        for(ProjectFile file : files)
        {
            file.test(tester);
        }
    }
}
