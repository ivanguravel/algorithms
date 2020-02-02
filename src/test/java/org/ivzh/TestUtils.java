package org.ivzh;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TestUtils {

    private static final String PATH = "D:\\projects\\Telegram\\algorithms\\src\\test\\java\\org\\ivzh\\testfiles\\test.%s.%s";

    private TestUtils() {}


    public static Map<String, Integer> createMapWithTestResults() {
        Map<String, Integer> result = new HashMap<>();
        String in;
        int out;
        for (int i =0; i < 5; i++) {
            in = readFile(String.format(PATH, String.valueOf(i), "in"));
            out = readFileAndConvert2Int(String.format(PATH, String.valueOf(i), "out"));
            result.put(in, out);
        }
        return result;
    }

    private static String readFile(String filePath) {
        Path myPath = Paths.get(filePath);
        try {
            Optional<String> first = Files.lines(myPath).findFirst();
            return first.orElse("");
        } catch (IOException e) {
            throw new UnsupportedOperationException(e.getMessage(), e);
        }
    }

    private static Integer readFileAndConvert2Int(String filePath) {
        return Integer.parseInt(readFile(filePath));
    }
}
