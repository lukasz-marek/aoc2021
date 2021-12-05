package aoc2021.day5;

import aoc2021.common.InputLoader;

public class Solver {
    private final InputLoader inputLoader;
    private final InputConverter inputConverter;
    private final IntersectionFinder intersectionFinder;

    public Solver(InputLoader inputLoader, InputConverter inputConverter, IntersectionFinder intersectionFinder) {
        this.inputLoader = inputLoader;
        this.inputConverter = inputConverter;
        this.intersectionFinder = intersectionFinder;
    }

    public int solve(String inputFile) {
        var input = inputLoader.loadInput(inputFile);
        var lines = inputConverter.convert(input);
        var intersections = intersectionFinder.findIntersections(lines);
        return intersections.size();
    }
}
