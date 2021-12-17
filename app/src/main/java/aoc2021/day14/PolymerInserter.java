package aoc2021.day14;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class PolymerInserter {
    private final Map<String, Character> insertionRules;

    public PolymerInserter(Map<String, Character> rules) {
        this.insertionRules = Map.copyOf(rules);
    }

    public Map<Character, BigInteger> insert(String template, int steps) {
        var polymerCounter = countPolymers(template);
        var pairCounter = countPairs(template);

        for (var step = 0; step < steps; step++)
            executeRules(pairCounter, polymerCounter);

        return polymerCounter;
    }

    private void executeRules(Map<String, BigInteger> pairs, Map<Character, BigInteger> polymers) {
        var outputPairs = new HashMap<String, BigInteger>();
        for (var rule : insertionRules.entrySet()) {
            if (pairs.containsKey(rule.getKey())) {
                executeRule(rule, pairs, polymers, outputPairs);
            }
        }
        pairs.clear();
        pairs.putAll(outputPairs);
    }

    private void executeRule(Map.Entry<String, Character> rule, Map<String, BigInteger> pairs, Map<Character, BigInteger> polymers, Map<String, BigInteger> outputPairs) {
        var pair = rule.getKey();
        var polymer = rule.getValue();
        var pairsCount = pairs.get(pair);

        updateCount(polymers, polymer, pairsCount);
        addNewPairs(pair, polymer, outputPairs, pairsCount);
    }

    private void addNewPairs(String pair, Character polymer, Map<String, BigInteger> pairs, BigInteger count) {
        var polymers = pair.toCharArray();
        var pair1 = String.valueOf(polymers[0]) + polymer;
        var pair2 = String.valueOf(polymer) + polymers[1];

        updateCount(pairs, pair1, count);
        updateCount(pairs, pair2, count);
    }

    private Map<String, BigInteger> countPairs(String template) {
        var output = new HashMap<String, BigInteger>();
        for (var endIndex = template.length() - 1; endIndex >= 1; endIndex--) {
            var startIndex = endIndex - 1;
            var pair = template.substring(startIndex, endIndex + 1);
            updateCount(output, pair, BigInteger.ONE);
        }
        return output;
    }

    private Map<Character, BigInteger> countPolymers(String template) {
        var output = new HashMap<Character, BigInteger>();
        for (var polymer : template.toCharArray())
            updateCount(output, polymer, BigInteger.ONE);
        return output;
    }

    private <K> void updateCount(Map<K, BigInteger> counter, K key, BigInteger by) {
        var currentValue = counter.getOrDefault(key, BigInteger.ZERO);
        counter.put(key, currentValue.add(by));
    }
}
