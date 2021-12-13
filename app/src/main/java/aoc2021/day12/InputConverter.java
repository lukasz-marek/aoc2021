package aoc2021.day12;

import java.util.*;
import java.util.regex.Pattern;

public class InputConverter {
    private final Pattern linePattern = Pattern.compile("^(.+)-(.+)$");

    public Map<String, Set<String>> convert(List<String> input) {
        var output = new HashMap<String, Set<String>>();

        for (var line : input)
            addMapping(output, line);
        addReverseMappings(output);

        return output;
    }

    private void addMapping(HashMap<String, Set<String>> output, String line) {
        var matcher = linePattern.matcher(line);
        if (matcher.find()) {
            var from = matcher.group(1);
            var to = matcher.group(2);
            output.putIfAbsent(from, new HashSet<>());
            output.get(from).add(to);
        }
    }

    private void addReverseMappings(HashMap<String, Set<String>> currentMapping) {
        HashMap<String, Set<String>> missingMappings = getMissingMappings(currentMapping);
        fillWithMissingMappings(currentMapping, missingMappings);
    }

    private void fillWithMissingMappings(HashMap<String, Set<String>> currentMapping, HashMap<String, Set<String>> missingMappings) {
        for (var entry : missingMappings.entrySet()) {
            currentMapping.putIfAbsent(entry.getKey(), new HashSet<>());
            currentMapping.get(entry.getKey()).addAll(entry.getValue());
        }
    }

    private HashMap<String, Set<String>> getMissingMappings(HashMap<String, Set<String>> currentMapping) {
        var missingMappings = new HashMap<String, Set<String>>();
        for (var entry : currentMapping.entrySet()) {
            for (var to : entry.getValue()) {
                missingMappings.putIfAbsent(to, new HashSet<>());
                missingMappings.get(to).add(entry.getKey());
            }
        }
        return missingMappings;
    }
}
