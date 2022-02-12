package aoc2021.day5;

import aoc2021.common.InputLoader;

import java.util.stream.Collectors;

public class Solver {
    private final InputLoader inputLoader;
    private final InputConverter inputConverter;
    private final IntersectionFinder intersectionFinder;

    public Solver(InputLoader inputLoader, InputConverter inputConverter, IntersectionFinder intersectionFinder) {
        this.inputLoader = inputLoader;
        this.inputConverter = inputConverter;
        this.intersectionFinder = intersectionFinder;
    }

    public long solveWithoutDiagonal(String inputFile) {
        var input = inputLoader.loadInput(inputFile);
        var lines = inputConverter.convert(input)
                .stream()
                .filter(line -> line.isVertical() || line.isHorizontal())
                .collect(Collectors.toUnmodifiableList());
        return intersectionFinder.findIntersections(lines);
    }

    public long solve(String inputFile) {
        var input = inputLoader.loadInput(inputFile);
        var lines = inputConverter.convert(input);
        return intersectionFinder.findIntersections(lines);
    }
}
