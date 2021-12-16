package aoc2021.day14;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PolymerInserter {
    private final Map<String, String> insertionRules;

    public PolymerInserter(Map<String, String> rules) {
        this.insertionRules = Map.copyOf(rules);
    }

    public String insert(String template, int steps) {
        var mutableTemplate = toPolymerList(template);
        for (var step = 0; step < steps; step++) {
            System.out.println((step + 1) + "/" + steps);
            executeRulesInParallel(mutableTemplate);
        }
        return String.join("", mutableTemplate);
    }

    private void executeRules(List<String> template) {
        for (var endIndex = template.size() - 1; endIndex >= 1; endIndex--) {
            var startIndex = endIndex - 1;
            var ruleKey = template.get(startIndex) + template.get(endIndex);
            attemptInsertion(template, endIndex, ruleKey);
        }
    }

    private void executeRulesInParallel(List<String> template) {
        var insertions = IntStream.range(1, template.size())
                .parallel()
                .mapToObj(endIndex -> {
                    var startIndex = endIndex - 1;
                    var key = template.get(startIndex) + template.get(endIndex);
                    var product = insertionRules.get(key);
                    return product != null ? Map.entry(endIndex, product) : null;
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(entry -> -1 * entry.getKey()))
                .collect(Collectors.toUnmodifiableList());

        for (var entry : insertions)
            template.add(entry.getKey(), entry.getValue());
    }

    private void attemptInsertion(List<String> template, int insertAt, String ruleKey) {
        var newPolymer = insertionRules.get(ruleKey);
        if (newPolymer != null) template.add(insertAt, newPolymer);
    }

    private List<String> toPolymerList(String template) {
        var output = new ArrayList<String>();
        for (var character : template.toCharArray())
            output.add(String.valueOf(character));
        return output;
    }
}
