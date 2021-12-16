package aoc2021.day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputConverter {
    public Input convert(List<String> rawInput) {
        var template = rawInput.get(0).trim();
        var rules = convertRules(rawInput.subList(2, rawInput.size()));
        return new Input(template, rules);
    }

    private Map<String, String> convertRules(List<String> rawRules) {
        var rules = new HashMap<String, String>();
        for (var rule : rawRules) {
            var parts = rule.trim().split(" -> ");
            rules.put(parts[0], parts[1]);
        }
        return rules;
    }
}
