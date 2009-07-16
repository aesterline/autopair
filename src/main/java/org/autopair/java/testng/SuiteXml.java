package org.autopair.java.testng;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class SuiteXml
{
    public static final String SUITE_TEMPLATE = "<!DOCTYPE suite SYSTEM \"http://testng.org/testng-1.0.dtd\">\n" +
                                                "<suite name=\"Custom suite\" parallel=\"none\">\n" +
                                                "  <test verbose=\"1\" name=\"agileide\" annotations=\"JDK\">\n" +
                                                "    <classes>\n" +
                                                "      <class name=\"%s\"/>\n" +
                                                "    </classes>\n" +
                                                "  </test>\n" +
                                                "</suite>";

    public File generateSuite(String classname)
    {
        try
        {
            File suiteXml = new File("/tmp/testng.xml");
            FileUtils.writeStringToFile(suiteXml, String.format(SUITE_TEMPLATE, classname));
            return suiteXml;
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
