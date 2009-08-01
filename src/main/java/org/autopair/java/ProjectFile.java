package org.autopair.java;

import org.autopair.Tester;

public interface ProjectFile
{
    void clean(Cleaner cleaner);

    void test(Tester tester);
}
