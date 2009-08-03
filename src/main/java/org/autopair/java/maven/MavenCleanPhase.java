package org.autopair.java.maven;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.autopair.Phase;

public class MavenCleanPhase implements Phase
{
    private Remove remove;

    @Inject
    public MavenCleanPhase(Remove remove)
    {
        this.remove = remove;
    }
//    private DeleteStrategy delegatee;
//
//    public MavenCleanPhase(DeleteStrategy delegatee)
//    {
//        this.delegatee = delegatee;
//    }
//
//    public void delete(File file)
//    {
//        String filename = file.getAbsolutePath();
//
//        filename = filename.replace("src/main/java", "target/classes");
//        filename = filename.replace("src/test/java", "target/test-classes");
//        filename = filename.replace(".java", ".class");
//
//        delegatee.delete(new File(filename));
//    }

    public void execute(List<File> updates)
    {
        List<File> filesToRemove = new ArrayList<File>();
        CollectionUtils.collect(
                updates,
                new Transformer()
                {
                    public Object transform(Object input)
                    {
                        File file = (File) input;

                        String filename = file.getAbsolutePath();

                        filename = filename.replace("src/main/java", "target/classes");
                        filename = filename.replace("src/test/java", "target/test-classes");
                        filename = filename.replace(".java", ".class");

                        return new File(filename);
                    }
                }, filesToRemove);

        remove.all(filesToRemove);
    }
}
