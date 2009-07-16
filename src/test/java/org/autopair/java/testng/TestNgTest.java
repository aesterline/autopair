package org.autopair.java.testng;

import java.io.File;

import org.autopair.exec.Executable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.testng.annotations.Test;

@Test
public class TestNgTest
{
    public void testShouldBeExecuted()
    {
        String classname = "org.autopair.JunkTest";
        File xmlFile = new File("temp.xml");

        Executable testngExe = mock(Executable.class);
        Executable testngWithSuite = mock(Executable.class);

        SuiteXml xml = mock(SuiteXml.class);
        when(xml.generateSuite(classname)).thenReturn(xmlFile);
        when(testngExe.addArguments(xmlFile.getAbsolutePath())).thenReturn(testngWithSuite);

        TestNg testng = new TestNg(testngExe, xml);
        testng.test(classname);

        verify(testngExe).addArguments(xmlFile.getAbsolutePath());
        verify(testngWithSuite).execute();
    }
}
