package aoc2021.day14;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PolymerInserter {
    private final Map<String, String> insertionRules;

    public PolymerInserter(Map<String, String> rules) {
        this.insertionRules = Map.copyOf(rules);
    }

    public String insert(String template, int steps) {
        var mutableTemplate = toPolymerList(template);
        for (var step = 0; step <= steps; step++)
            executeRules(mutableTemplate);
        return String.join("", mutableTemplate);
    }

    private void executeRules(List<String> template) {
        for (var endIndex = template.size() - 1; endIndex >= 1; endIndex--) {
            var startIndex = endIndex + 1;
            var ruleKey = template.get(startIndex) + template.get(endIndex);
            attemptInsertion(template, endIndex, ruleKey);
        }
    }

    private void attemptInsertion(List<String> template, int insertAt, String ruleKey) {
        var newPolymer = insertionRules.get(ruleKey);
        if (newPolymer != null)
            template.add(insertAt, newPolymer);
    }

    private List<String> toPolymerList(String template) {
        var output = new ArrayList<String>();
        for (var character : template.toCharArray())
            output.add(String.valueOf(character));
        return output;
    }
}
