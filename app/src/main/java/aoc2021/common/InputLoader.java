package aoc2021.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InputLoader {

    private final static String INPUTS_DIR_NAME = "inputs";

    public List<String> loadInput(String fileName) {
        try (var reader = getReader(fileName)) {
            return reader.lines().collect(Collectors.toUnmodifiableList());
        } catch (IOException e) {
            throw new InputLoaderException(e);
        }
    }

    private BufferedReader getReader(String fileName) {
        var inputStream = getClass().getClassLoader().getResourceAsStream(INPUTS_DIR_NAME + "/" + fileName);
        Objects.requireNonNull(inputStream, "File " + fileName + "does not exist.");
        return new BufferedReader(new InputStreamReader(inputStream));
    }
}
