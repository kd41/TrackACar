package ee.track.helpers;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

import ee.track.model.Data;

public class DataIO {
    public static final String CHANGES_FILE_NAME = "resources/changes.txt";
    public static final String LINKS_FILE_NAME = "resources/links.txt";

    private static final String RESULT_FILE_NAME = "resources/results.txt";

    public static void saveResults(List<Data> results) throws IOException {
        FileUtils.writeLines(new File(RESULT_FILE_NAME), results, false);
    }

    public static void save(Collection<?> lines) throws IOException {
        FileUtils.writeLines(new File(CHANGES_FILE_NAME), lines, true);
    }

    public static List<String> getUrls() throws IOException {
        return FileUtils.readLines(new File(LINKS_FILE_NAME));
    }

    public static List<String> getChanges() throws IOException {
        return FileUtils.readLines(new File(CHANGES_FILE_NAME));
    }
}
