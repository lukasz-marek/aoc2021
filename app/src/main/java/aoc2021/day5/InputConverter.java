package aoc2021.day5;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputConverter {
    private final Pattern linePattern = Pattern.compile("^(\\d+),(\\d+) -> (\\d+),(\\d+)$");

    public List<Line> convert(List<String> input) {
        return input.stream()
                .map(this::convert)
                .collect(Collectors.toUnmodifiableList());
    }

    private Line convert(String rawData) {
        var match = linePattern.matcher(rawData.trim());
        if (match.find()) {
            var from = convertPoint(match.group(1), match.group(2));
            var to = convertPoint(match.group(3), match.group(4));
            return new Line(from, to);
        }
        throw new IllegalArgumentException("Cannot convert: " + rawData);
    }

    private Point convertPoint(String x, String y) {
        return new Point(Integer.parseInt(x), Integer.parseInt(y));
    }
}
