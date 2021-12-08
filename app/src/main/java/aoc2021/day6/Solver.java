package aoc2021.day6;

import aoc2021.common.InputLoader;

public class Solver {
    private final InputLoader inputLoader;
    private final InputConverter inputConverter;
    private final SimulationRunner simulationRunner;

    public Solver(InputLoader inputLoader, InputConverter inputConverter, SimulationRunner simulationRunner) {
        this.inputLoader = inputLoader;
        this.inputConverter = inputConverter;
        this.simulationRunner = simulationRunner;
    }

    public long solve(long steps) {
        var rawInput = inputLoader.loadInput("day6_1.txt");
        var convertedInput = inputConverter.convert(rawInput);
        var simulationResult = simulationRunner.runSimulation(convertedInput, steps);
        return simulationResult.values().stream().mapToLong(Long::longValue).sum();
    }
}
