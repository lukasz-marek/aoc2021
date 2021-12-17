package aoc2021.day14;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.Map;

public class PolymerAnalyzer {

    public BigInteger countMostCommon(Map<Character, BigInteger> template) {
        return template.values().stream().max(Comparator.naturalOrder()).get();
    }

    public BigInteger countLeastCommon(Map<Character, BigInteger> template) {
        return template.values().stream().min(Comparator.naturalOrder()).get();
    }
}
