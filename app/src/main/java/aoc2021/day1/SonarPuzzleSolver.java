package aoc2021.day1;

import aoc2021.common.InputLoader;

public class SonarPuzzleSolver {
    private final static String INPUT_1_NAME = "day1_1.txt";

    private final SonarAnalyzer sonarAnalyzer;
    private final SonarMeasurementsConverter sonarMeasurementsConverter;
    private final InputLoader inputLoader;

    public SonarPuzzleSolver(SonarAnalyzer sonarAnalyzer, SonarMeasurementsConverter sonarMeasurementsConverter, InputLoader inputLoader) {
        this.sonarAnalyzer = sonarAnalyzer;
        this.sonarMeasurementsConverter = sonarMeasurementsConverter;
        this.inputLoader = inputLoader;
    }

    public int solve() {
        var puzzleInput = inputLoader.loadInput(INPUT_1_NAME);
        var sonarMeasurements = sonarMeasurementsConverter.convert(puzzleInput);
        return sonarAnalyzer.countNumberOfDepthIncreases(sonarMeasurements);
    }
}
