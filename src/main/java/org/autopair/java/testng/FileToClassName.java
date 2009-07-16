package org.autopair.java.testng;

import java.io.File;

public class FileToClassName
{
    private String prefix;

    public FileToClassName(String prefix)
    {
        this.prefix = prefix;
    }

    public String convertToClassName(File file)
    {
        String classname = file.getAbsolutePath().replace(prefix, "");
        classname = classname.replace(".java", "");
        classname = classname.replace(File.separator, ".");
        if(classname.startsWith(".")) classname = classname.replaceFirst(".", "");
        return classname;
    }
}
