package aoc2021.day12;

import aoc2021.common.InputLoader;

public class Solver {
    private final InputLoader inputLoader;
    private final InputConverter inputConverter;
    private final PathCounter pathCounter;

    public Solver() {
        inputConverter = new InputConverter();
        inputLoader = new InputLoader();
        pathCounter = new PathCounter();
    }

    public long countPaths(int maxVisits) {
        var input = inputLoader.loadInput("day12_1.txt");
        var waypoints = inputConverter.convert(input);
        return pathCounter.countPaths(waypoints, maxVisits);
    }
}
