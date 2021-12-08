package aoc2021.day6;

import aoc2021.common.InputLoader;

public class Main {
    public static void main(String[] args) {
        var loader = new InputLoader();
        var converter = new InputConverter();
        var simulationRunner = new SimulationRunner(new LanternfishSimulator(), new DataOptimizer());
        var solver = new Solver(loader, converter, simulationRunner);

        System.out.println("Part 1: " + solver.solve(80));
        System.out.println("Part 1: " + solver.solve(256));
    }
}
