package aoc2021.day5;

import aoc2021.common.InputLoader;

public class Main {

    public static void main(String[] args) {
        var loader = new InputLoader();
        var converter = new InputConverter();
        var finder = new IntersectionFinder();
        var solver = new Solver(loader, converter, finder);

        System.out.println("Part1: " + solver.solveWithoutDiagonal("day5_1.txt"));
        System.out.println("Part2: " + solver.solve("day5_1.txt"));
    }
}
