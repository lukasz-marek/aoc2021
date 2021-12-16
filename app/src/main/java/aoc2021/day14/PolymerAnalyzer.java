package aoc2021.day14;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class PolymerAnalyzer {

    public int countMostCommon(String template) {
        var counter = countCharacters(template);
        return counter.values().stream().max(Comparator.naturalOrder()).get();
    }

    public int countLeastCommon(String template) {
        var counter = countCharacters(template);
        return counter.values().stream().min(Comparator.naturalOrder()).get();
    }

    private HashMap<Character, Integer> countCharacters(String template) {
        var result = new HashMap<Character, Integer>();
        for (var i = 0; i < template.length(); i++)
            update(result, template.charAt(i));
        return result;
    }

    private void update(Map<Character, Integer> counter, Character value) {
        var current = counter.getOrDefault(value, 0);
        counter.put(value, current + 1);
    }
}
