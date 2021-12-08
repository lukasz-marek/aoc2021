package aoc2021.day7;

import aoc2021.common.InputLoader;

import java.util.stream.IntStream;

public class Solver {
    private final InputLoader inputLoader;
    private final InputConverter inputConverter;
    private final Optimizer optimizer;

    public Solver(InputLoader inputLoader, InputConverter inputConverter, Optimizer optimizer) {
        this.inputLoader = inputLoader;
        this.inputConverter = inputConverter;
        this.optimizer = optimizer;
    }


    public long solvePart1() {
        var inputData = inputLoader.loadInput("day7_1.txt");
        var processedInput = inputConverter.convert(inputData);
        return optimizer.findCheapestPositionCost(processedInput, (i) -> (long) i);
    }

    public long solvePart2() {
        var inputData = inputLoader.loadInput("day7_1.txt");
        var processedInput = inputConverter.convert(inputData);
        return optimizer.findCheapestPositionCost(processedInput, this::computePart2Cost);
    }

    private long computePart2Cost(int distance) {
        return IntStream.rangeClosed(0, distance).sum();
    }
}
