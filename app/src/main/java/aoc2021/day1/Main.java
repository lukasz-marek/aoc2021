package aoc2021.day1;

import aoc2021.common.InputLoader;

public class Main {
    private final static String INPUT_NAME = "day1_1.txt";

    public static void main(String[] args) {
        var inputLoader = new InputLoader();
        var basicConverter = new BasicSonarMeasurementsConverter();
        var analyzer = new SonarAnalyzer();

        var solver1 = new SonarPuzzleSolver(analyzer, basicConverter, inputLoader);
        System.out.println("Part 1: " + solver1.solve(INPUT_NAME));

        var windowedConverter = new WindowedSonarMeasurementsConverter(basicConverter);

        var solver2 = new SonarPuzzleSolver(analyzer, windowedConverter, inputLoader);
        System.out.println("Part 2: " + solver2.solve(INPUT_NAME));
    }
}
