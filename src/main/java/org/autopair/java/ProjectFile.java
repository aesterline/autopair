package org.autopair.java;

public interface ProjectFile
{
    void clean(Cleaner cleaner);

    void test(Tester tester);
}
