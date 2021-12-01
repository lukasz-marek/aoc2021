package aoc2021.day1;

import aoc2021.common.InputLoader;

public class Main {
    public static void main(String[] args) {
        var inputLoader = new InputLoader();
        var converter = new SonarMeasurementsConverter();
        var analyzer = new SonarAnalyzer();

        var solver = new SonarPuzzleSolver(analyzer, converter, inputLoader);

        System.out.println(solver.solve());
    }
}
