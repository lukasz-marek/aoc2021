package aoc2021.day2;

import aoc2021.common.InputLoader;

public class Solver {
    private final InputLoader inputLoader;
    private final StepConverter stepConverter;
    private final StepExecutor stepExecutor;

    public Solver(InputLoader inputLoader, StepConverter stepConverter, StepExecutor stepExecutor) {
        this.inputLoader = inputLoader;
        this.stepConverter = stepConverter;
        this.stepExecutor = stepExecutor;
    }

    public int solve(String fileName) {
        var input = inputLoader.loadInput(fileName);
        var steps = stepConverter.convert(input);
        var lastPosition = stepExecutor.execute(steps);
        return lastPosition.getDepth() * lastPosition.getHorizontal();
    }
}
