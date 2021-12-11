package aoc2021.day9;

import aoc2021.common.InputLoader;

import java.util.Set;

public class Solver {
    private final InputLoader inputLoader;
    private final LowPointFinder lowPointFinder;
    private final InputConverter converter;
    private final BasinFinder basinFinder;

    public Solver() {
        converter = new InputConverter();
        lowPointFinder = new LowPointFinder();
        inputLoader = new InputLoader();
        basinFinder = new BasinFinder();
    }

    public int solvePart1() {
        var input = inputLoader.loadInput("day9_1.txt");
        var heightmap = converter.convert(input);
        var lowPoints = lowPointFinder.find(heightmap);
        return lowPoints.stream().mapToInt(Coordinates::getHeight).map(height -> height + 1).sum();
    }

    public long solvePart2() {
        var input = inputLoader.loadInput("day9_1.txt");
        var heightmap = converter.convert(input);
        var lowPoints = lowPointFinder.find(heightmap);
        var basins = basinFinder.find(heightmap, lowPoints);
        return basins.stream()
                .map(Basin::getMembers)
                .mapToLong(Set::size)
                .sorted()
                .skip(basins.size() - 3)
                .reduce((x, y) -> x * y)
                .getAsLong();
    }
}
