package org.autopair.monitor.git;

public class GitStatusSamples
{
    public static final String UNTRACKED_FILE = "# On branch master\n" +
                                                "#\n" +
                                                "# Initial commit\n" +
                                                "#\n" +
                                                "# Untracked files:\n" +
                                                "#   (use \"git add <file>...\" to include in what will be committed)\n" +
                                                "#\n" +
                                                "#\tpom.xml\n" +
                                                "#";
    public static final String MULTIPLE_UNTRACKED_FILES = "# On branch master\n" +
                                                          "#\n" +
                                                          "# Initial commit\n" +
                                                          "#\n" +
                                                          "# Untracked files:\n" +
                                                          "#   (use \"git add <file>...\" to include in what will be committed)\n" +
                                                          "#\n" +
                                                          "#\tpom.xml\n" +
                                                          "#\tjunk/me/you/cool.txt\n" +
                                                          "#";

    public static final String TRACKED_FILE = "# On branch master\n" +
                                              "# Changed but not updated:\n" +
                                              "#   (use \"git add <file>...\" to update what will be committed)\n" +
                                              "#   (use \"git checkout -- <file>...\" to discard changes in working directory)\n" +
                                              "#\n" +
                                              "#\tmodified:   src/test/java/org/agileide/monitor/git/GitFileSystemMonitorTest.java\n" +
                                              "#";

    public static final String MULTIPLE_TRACKED_FILES = "# On branch master\n" +
                                                        "# Changed but not updated:\n" +
                                                        "#   (use \"git add <file>...\" to update what will be committed)\n" +
                                                        "#   (use \"git checkout -- <file>...\" to discard changes in working directory)\n" +
                                                        "#\n" +
                                                        "#\tmodified:   src/main/java/org/agileide/monitor/FileSystemChangeListener.java\n" +
                                                        "#\tmodified:   src/test/java/org/agileide/monitor/git/GitStatusSamples.java\n" +
                                                        "#";

    public static final String DELETED_TRACKED_FILE = "# On branch master\n" +
                                                      "# Changed but not updated:\n" +
                                                      "#   (use \"git add/rm <file>...\" to update what will be committed)\n" +
                                                      "#   (use \"git checkout -- <file>...\" to discard changes in working directory)\n" +
                                                      "#\n" +
                                                      "#\tdeleted:    pom.xml\n" +
                                                      "#";

    public static final String MULTIPLE_DELETED_TRACKED_FILES = "# On branch master\n" +
                                                                "# Changed but not updated:\n" +
                                                                "#   (use \"git add/rm <file>...\" to update what will be committed)\n" +
                                                                "#   (use \"git checkout -- <file>...\" to discard changes in working directory)\n" +
                                                                "#\n" +
                                                                "#\tdeleted:    pom.xml\n" +
                                                                "#\tdeleted:    src/test/java/org/agileide/monitor/git/GitStatusSamples.java\n" +
                                                                "#";

    public static final String MIXED_TRACKED_AND_UNTRACKED = "# On branch master\n" +
                                                             "# Changed but not updated:\n" +
                                                             "#   (use \"git add/rm <file>...\" to update what will be committed)\n" +
                                                             "#   (use \"git checkout -- <file>...\" to discard changes in working directory)\n" +
                                                             "#\n" +
                                                             "#\tdeleted:    pom.xml\n" +
                                                             "#\n" +
                                                             "# Untracked files:\n" +
                                                             "#   (use \"git add <file>...\" to include in what will be committed)\n" +
                                                             "#\n" +
                                                             "#\tjunk/for/me/yes.txt";

    public static final String NEW_FILE_IN_INDEX = "# On branch master\n" +
                                                   "# Changes to be committed:\n" +
                                                   "#   (use \"git reset HEAD <file>...\" to unstage)\n" +
                                                   "#\n" +
                                                   "#\tnew file:   junk.txt\n" +
                                                   "#";

    public static final String MULTIPLE_FILES_IN_INDEX = "# On branch master\n" +
                                                         "# Changes to be committed:\n" +
                                                         "#   (use \"git reset HEAD <file>...\" to unstage)\n" +
                                                         "#\n" +
                                                         "#\tmodified:   README\n" +
                                                         "#\tnew file:   junk.txt\n" +
                                                         "#";

    public static final String RENAMED_FILE_IN_INDEX = "# On branch master\n" +
                                                       "# Changes to be committed:\n" +
                                                       "#   (use \"git reset HEAD <file>...\" to unstage)\n" +
                                                       "#\n" +
                                                       "#\trenamed:    pom.xml -> job.xml\n" +
                                                       "#";
}
