package aoc2021.day3;

import aoc2021.common.InputLoader;

public class Main {
    public static void main(String[] args) {
        var loader = new InputLoader();
        var analyzer = new ReportAnalyzer();
        var solver = new Solver(loader, analyzer);
        var fileName = "day3_1.txt";

        System.out.println("Part 1: " + solver.solve(fileName));
    }
}
