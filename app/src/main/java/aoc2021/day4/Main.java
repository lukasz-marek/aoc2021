package aoc2021.day4;

import aoc2021.common.InputLoader;

public class Main {
    public static void main(String[] args) {
        var inputLoader = new InputLoader();
        var inputConverter = new InputConverter();
        var executor = new BingoExecutor();
        var solver = new Solver(inputLoader, inputConverter, executor);

        System.out.println("Part 1: " + solver.solveFirstWinner("day4_1.txt"));
        System.out.println("Part 2: " + solver.solveLastWinner("day4_1.txt"));
    }
}
