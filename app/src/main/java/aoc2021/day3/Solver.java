package aoc2021.day3;

import aoc2021.common.InputLoader;

import java.util.stream.Collectors;

public class Solver {
    private final InputLoader inputLoader;
    private final ReportAnalyzer reportAnalyzer;


    public Solver(InputLoader inputLoader, ReportAnalyzer reportAnalyzer) {
        this.inputLoader = inputLoader;
        this.reportAnalyzer = reportAnalyzer;
    }

    public long solve(String inputFile) {
        var rawData = inputLoader.loadInput(inputFile);
        var reports = rawData.stream()
                .map(ReportEntry::new)
                .collect(Collectors.toUnmodifiableList());
        var result = reportAnalyzer.analyze(reports);
        return result.getEpsilon() * result.getGamma();
    }
}
