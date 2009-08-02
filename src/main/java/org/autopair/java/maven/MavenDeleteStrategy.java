package org.autopair.java.maven;

import java.io.File;

import org.autopair.java.DeleteStrategy;

public class MavenDeleteStrategy implements DeleteStrategy
{
    private DeleteStrategy delegatee;

    public MavenDeleteStrategy(DeleteStrategy delegatee)
    {
        this.delegatee = delegatee;
    }

    public void delete(File file)
    {
        String filename = file.getAbsolutePath();

        filename = filename.replace("src/main", "target/classes");
        filename = filename.replace("src/test", "target/test-classes");

        delegatee.delete(new File(filename));
    }
}
