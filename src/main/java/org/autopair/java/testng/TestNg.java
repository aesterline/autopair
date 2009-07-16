package org.autopair.java.testng;

import java.io.File;

import org.autopair.exec.Executable;

public class TestNg
{
    private Executable testngExe;
    private SuiteXml xml;

    public TestNg(Executable testngExe, SuiteXml xml)
    {
        this.testngExe = testngExe;
        this.xml = xml;
    }

    public void test(String classname)
    {
        File suiteXml = xml.generateSuite(classname);
        testngExe.addArguments(suiteXml.getAbsolutePath()).execute();
    }
}
