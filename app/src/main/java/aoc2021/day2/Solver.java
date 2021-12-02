package aoc2021.day2;

import aoc2021.common.InputLoader;

public class Solver {
    private final InputLoader inputLoader;
    private final StepConverter stepConverter;
    private final StepExecutor stepExecutor;

    private final Position initialPosition = new Position();
    private final AimedPosition initialAimedPosition = new AimedPosition();

    public Solver(InputLoader inputLoader, StepConverter stepConverter, StepExecutor stepExecutor) {
        this.inputLoader = inputLoader;
        this.stepConverter = stepConverter;
        this.stepExecutor = stepExecutor;
    }

    public int solve(String fileName) {
        var input = inputLoader.loadInput(fileName);
        var steps = stepConverter.convert(input);
        var lastPosition = stepExecutor.execute(steps, initialPosition);
        return lastPosition.getDepth() * lastPosition.getHorizontal();
    }

    public int solveAimed(String fileName) {
        var input = inputLoader.loadInput(fileName);
        var steps = stepConverter.convert(input);
        var lastPosition = stepExecutor.execute(steps, initialAimedPosition);
        return lastPosition.getDepth() * lastPosition.getHorizontal();
    }
}
