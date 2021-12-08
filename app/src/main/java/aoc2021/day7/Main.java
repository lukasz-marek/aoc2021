package aoc2021.day7;

import aoc2021.common.InputLoader;

public class Main {
    public static void main(String[] args) {
        var loader = new InputLoader();
        var converter = new InputConverter();
        var optimizer = new Optimizer();
        var solver = new Solver(loader, converter, optimizer);

        System.out.println("Part 1: " + solver.solvePart1());
        System.out.println("Part 2: " + solver.solvePart2());
    }
}
