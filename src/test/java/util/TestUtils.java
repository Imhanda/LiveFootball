package util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class TestUtils {

    /**
     * Fetch JSON String from resources file
     *
     * @param fileName - Name of the file to be fetched from the test/resources folder
     * @return JSON String
     */
    public static String getExpectedScoreboard(String fileName) throws IOException {
        File file = new File(Objects.requireNonNull(TestUtils.class.getClassLoader().getResource(fileName)).getFile());
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }
}
