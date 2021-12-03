package aoc2021.day3;

import aoc2021.common.InputLoader;

public class Main {
    public static void main(String[] args) {
        var loader = new InputLoader();
        var analyzer = new ReportAnalyzer();
        var gasAnalyzer = new GasAnalyzer();
        var solver = new Solver(loader, analyzer, gasAnalyzer);
        var fileName = "day3_1.txt";

        System.out.println("Part 1: " + solver.solve(fileName));
        System.out.println("Part 2: " + solver.solveGas(fileName));
    }
}
