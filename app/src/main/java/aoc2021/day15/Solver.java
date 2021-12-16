package aoc2021.day15;

import aoc2021.common.InputLoader;

public class Solver {
    private final InputLoader inputLoader;
    private final InputConverter inputConverter;
    private final PathFinder pathFinder;
    private final RiskCalculator riskCalculator;

    public Solver() {
        inputLoader = new InputLoader();
        inputConverter = new InputConverter();
        pathFinder = new PathFinder();
        riskCalculator = new RiskCalculator();
    }

    public long solvePart1() {
        var rawInput = inputLoader.loadInput("day15_1.txt");
        var map = inputConverter.convert(rawInput);
        var path = pathFinder.findPathWithLowestRisk(map);
        return riskCalculator.calculateRisk(path, map);
    }
}
