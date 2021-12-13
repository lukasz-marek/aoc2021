package aoc2021.day11;

import aoc2021.common.InputLoader;

public class Solver {
    private final InputLoader inputLoader;
    private final InputConverter inputConverter;
    private final StepExecutor stepExecutor;

    public Solver() {
        inputConverter = new InputConverter();
        stepExecutor = new StepExecutor();
        inputLoader = new InputLoader();
    }

    public long solvePart1(){
        var inputData = inputLoader.loadInput("day11_1.txt");
        var grid = inputConverter.convert(inputData);
        return stepExecutor.execute(grid, 100);
    }
}
