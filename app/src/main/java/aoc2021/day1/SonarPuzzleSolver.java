package aoc2021.day1;

import aoc2021.common.InputLoader;

public class SonarPuzzleSolver {

    private final SonarAnalyzer sonarAnalyzer;
    private final SonarMeasurementsConverter basicSonarMeasurementsConverter;
    private final InputLoader inputLoader;

    public SonarPuzzleSolver(SonarAnalyzer sonarAnalyzer, SonarMeasurementsConverter basicSonarMeasurementsConverter, InputLoader inputLoader) {
        this.sonarAnalyzer = sonarAnalyzer;
        this.basicSonarMeasurementsConverter = basicSonarMeasurementsConverter;
        this.inputLoader = inputLoader;
    }

    public int solve(String inputName) {
        var puzzleInput = inputLoader.loadInput(inputName);
        var sonarMeasurements = basicSonarMeasurementsConverter.convert(puzzleInput);
        return sonarAnalyzer.countNumberOfDepthIncreases(sonarMeasurements);
    }
}
