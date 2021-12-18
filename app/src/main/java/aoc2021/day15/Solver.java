package aoc2021.day15;

import aoc2021.common.InputLoader;

public class Solver {
    private final InputLoader inputLoader;
    private final InputConverter inputConverter;
    private final PathFinder pathFinder;
    private final RiskCalculator riskCalculator;
    private final MapResizer mapResizer;

    public Solver() {
        inputLoader = new InputLoader();
        inputConverter = new InputConverter();
        pathFinder = new PathFinder();
        riskCalculator = new RiskCalculator();
        mapResizer = new MapResizer();
    }

    public long solvePart1() {
        var rawInput = inputLoader.loadInput("day15_1.txt");
        var map = inputConverter.convert(rawInput);
        var path = pathFinder.findPathWithLowestRisk(map);
        return riskCalculator.calculateRisk(path, map);
    }

    public long solvePart2() {
        var rawInput = inputLoader.loadInput("day15_1.txt");
        var map = inputConverter.convert(rawInput);
        var resizedMap = mapResizer.resize(map, 5);
        var path = pathFinder.findPathWithLowestRisk(resizedMap);
        return riskCalculator.calculateRisk(path, resizedMap);
    }
}
