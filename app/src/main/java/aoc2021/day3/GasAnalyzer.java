package aoc2021.day3;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class GasAnalyzer {
    public GasAnalysisResult analyze(List<ReportEntry> reports) {
        var generatorRating = decodeGeneratorRating(reports);
        var scrubberRating = decodeScrubberRating(reports);
        return new GasAnalysisResult(generatorRating, scrubberRating);
    }

    private long decodeGeneratorRating(List<ReportEntry> reports) {
        return decodeRating(reports, this::findMostCommonSymbol);
    }

    private long decodeScrubberRating(List<ReportEntry> reports) {
        return decodeRating(reports, this::findLeastCommonSymbol);
    }

    private long decodeRating(List<ReportEntry> reports, BiFunction<List<ReportEntry>, Integer, Character> selector) {
        var index = 0;
        while (reports.size() > 1) {
            reports = filterUnwantedReports(reports, selector, index);
            index++;
        }
        var chosenReport = reports.get(0);
        return Long.valueOf(chosenReport.getValue(), 2);
    }

    private List<ReportEntry> filterUnwantedReports(List<ReportEntry> reports, BiFunction<List<ReportEntry>, Integer, Character> selector, int filterIndex) {
        var selectedSymbol = selector.apply(reports, filterIndex);
        return reports.stream()
                .filter(report -> report.getValue().charAt(filterIndex) == selectedSymbol)
                .collect(Collectors.toUnmodifiableList());
    }

    private char findMostCommonSymbol(List<ReportEntry> reports, int index) {
        var ones = countOnes(reports, index);
        var zeros = reports.size() - ones;

        if (ones >= zeros)
            return '1';
        else
            return '0';
    }

    private char findLeastCommonSymbol(List<ReportEntry> reports, int index) {
        var ones = countOnes(reports, index);
        var zeros = reports.size() - ones;

        if (ones < zeros)
            return '1';
        else
            return '0';
    }

    private int countOnes(List<ReportEntry> reports, int index) {
        var ones = 0;
        for (var report : reports) {
            if (report.getValue().charAt(index) == '1')
                ones++;
        }
        return ones;
    }
}
