package aoc2021.day3;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReportAnalyzer {
    private final Function<Integer, Map<ByteValue, Integer>> emptyMapProvider = (Integer key) -> new HashMap<>();

    private enum ByteValue {
        ZERO {
            @Override
            char charValue() {
                return '0';
            }
        }, ONE {
            @Override
            char charValue() {
                return '1';
            }
        };

        abstract char charValue();
    }

    public AnalysisResult analyze(List<ReportEntry> reports) {
        var subReports = reports.stream()
                .map(this::entryToSubReport)
                .collect(Collectors.toUnmodifiableList());
        var report = summarizeReports(subReports);

        var gamma = computeGamma(report);
        var epsilon = computeEpsilon(report);

        return new AnalysisResult(gamma, epsilon);
    }

    private Map<Integer, ByteValue> entryToSubReport(ReportEntry entry) {
        var report = new HashMap<Integer, ByteValue>();
        for (var i = 0; i < entry.getValue().length(); i++) {
            var symbol = entry.getValue().charAt(i);
            report.put(i, byteFor(symbol));
        }
        return report;
    }

    private ByteValue byteFor(char symbol) {
        switch (symbol) {
            case '0':
                return ByteValue.ZERO;
            case '1':
                return ByteValue.ONE;
            default:
                throw new IllegalArgumentException("Unsupported value: " + symbol);
        }
    }

    private Map<Integer, Map<ByteValue, Integer>> summarizeReports(List<Map<Integer, ByteValue>> reports) {
        var finalReport = new HashMap<Integer, Map<ByteValue, Integer>>();

        for (var part : reports)
            for (var entry : part.entrySet())
                updateFinalReport(finalReport, entry);

        return finalReport;
    }

    private void updateFinalReport(Map<Integer, Map<ByteValue, Integer>> finalReport, Map.Entry<Integer, ByteValue> entry) {
        var reportEntry = finalReport.computeIfAbsent(entry.getKey(), emptyMapProvider);
        var newValue = reportEntry.getOrDefault(entry.getValue(), 0) + 1;
        reportEntry.put(entry.getValue(), newValue);
    }

    private long computeGamma(Map<Integer, Map<ByteValue, Integer>> finalReport) {
        return computeParameter(finalReport, this::chooseMostCommonByte);
    }

    private long computeEpsilon(Map<Integer, Map<ByteValue, Integer>> finalReport) {
        return computeParameter(finalReport, this::chooseLeastCommonByte);
    }

    private long computeParameter(Map<Integer, Map<ByteValue, Integer>> finalReport, Function<Map.Entry<Integer, Map<ByteValue, Integer>>, Map.Entry<Integer, ByteValue>> selector) {
        var byteValues = finalReport.entrySet().stream()
                .map(selector)
                .sorted(Comparator.comparingInt(Map.Entry::getKey))
                .map(Map.Entry::getValue)
                .collect(Collectors.toUnmodifiableList());

        var stringValue = byteValues.stream()
                .map(ByteValue::charValue)
                .map(String::valueOf)
                .collect(Collectors.joining());

        return Long.valueOf(stringValue, 2);
    }

    private Map.Entry<Integer, ByteValue> chooseMostCommonByte(Map.Entry<Integer, Map<ByteValue, Integer>> entry) {
        var counterForPosition = entry.getValue();

        if (counterForPosition.get(ByteValue.ZERO) > counterForPosition.get(ByteValue.ONE))
            return Map.entry(entry.getKey(), ByteValue.ZERO);
        else
            return Map.entry(entry.getKey(), ByteValue.ONE);
    }

    private Map.Entry<Integer, ByteValue> chooseLeastCommonByte(Map.Entry<Integer, Map<ByteValue, Integer>> entry) {
        var counterForPosition = entry.getValue();

        if (counterForPosition.get(ByteValue.ZERO) < counterForPosition.get(ByteValue.ONE))
            return Map.entry(entry.getKey(), ByteValue.ZERO);
        else
            return Map.entry(entry.getKey(), ByteValue.ONE);
    }
}
