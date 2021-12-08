package aoc2021.day7;

import aoc2021.common.InputLoader;

public class Solver {
    private final InputLoader inputLoader;
    private final InputConverter inputConverter;
    private final Optimizer optimizer;

    public Solver(InputLoader inputLoader, InputConverter inputConverter, Optimizer optimizer) {
        this.inputLoader = inputLoader;
        this.inputConverter = inputConverter;
        this.optimizer = optimizer;
    }


    public int solve() {
        var inputData = inputLoader.loadInput("day7_1.txt");
        var processedInput = inputConverter.convert(inputData);
        return optimizer.findCheapestPositionCost(processedInput);
    }
}
