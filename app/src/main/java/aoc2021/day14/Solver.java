package aoc2021.day14;

import aoc2021.common.InputLoader;

import java.math.BigInteger;

public class Solver {
    private final InputLoader inputLoader;
    private final InputConverter inputConverter;
    private final PolymerAnalyzer analyzer;

    public Solver() {
        inputLoader = new InputLoader();
        inputConverter = new InputConverter();
        analyzer = new PolymerAnalyzer();
    }

    public BigInteger solvePart1() {
        var rawInput = inputLoader.loadInput("day14_1.txt");
        var input = inputConverter.convert(rawInput);
        var inserter = new PolymerInserter(input.getRules());
        var resultPolymer = inserter.insert(input.getTemplate(), 10);
        var max = analyzer.countMostCommon(resultPolymer);
        var min = analyzer.countLeastCommon(resultPolymer);
        return max.subtract(min);
    }

    public BigInteger solvePart2() {
        var rawInput = inputLoader.loadInput("day14_1.txt");
        var input = inputConverter.convert(rawInput);
        var inserter = new PolymerInserter(input.getRules());
        var resultPolymer = inserter.insert(input.getTemplate(), 40);
        var max = analyzer.countMostCommon(resultPolymer);
        var min = analyzer.countLeastCommon(resultPolymer);
        return max.subtract(min);
    }
}
