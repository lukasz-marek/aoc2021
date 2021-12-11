package aoc2021.day9;

import aoc2021.common.InputLoader;

public class Solver {
    private final InputLoader inputLoader;
    private final LowPointFinder lowPointFinder;
    private final InputConverter converter;

    public Solver() {
        converter = new InputConverter();
        lowPointFinder = new LowPointFinder();
        inputLoader = new InputLoader();
    }

    public int solvePart1() {
        var input = inputLoader.loadInput("day9_1.txt");
        var heightmap = converter.convert(input);
        var lowPoints = lowPointFinder.find(heightmap);
        return lowPoints.stream().mapToInt(Coordinates::getHeight).map(height -> height + 1).sum();
    }
}
