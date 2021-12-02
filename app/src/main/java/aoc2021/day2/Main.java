package aoc2021.day2;

import aoc2021.common.InputLoader;

public class Main {
    private final static String DAY2_INPUT = "day2_1.txt";

    public static void main(String[] args) {
        var inputLoader = new InputLoader();
        var stepConverter = new StepConverter();
        var stepExecutor = new StepExecutor();
        var solver = new Solver(inputLoader, stepConverter, stepExecutor);

        System.out.println("Part 1: " + solver.solve(DAY2_INPUT));
        System.out.println("Part 2: " + solver.solveAimed(DAY2_INPUT));
    }
}
