package aoc2021.day2;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StepConverter {
    private final static String FORWARD = "forward";
    private final static String UP = "up";
    private final static String DOWN = "down";

    private final Pattern stepPattern = Pattern.compile("^(up|down|forward) (\\d+)$");

    public List<Step> convert(List<String> rawData) {
        return rawData.stream()
                .map(this::convert)
                .collect(Collectors.toUnmodifiableList());
    }

    private Step convert(String rawStep) {
        var matcher = stepPattern.matcher(rawStep);
        if (matcher.find()) {
            var name = matcher.group(1);
            var offset = Integer.parseInt(matcher.group(2));
            return buildStep(name, offset);
        } else throw new IllegalArgumentException("Invalid input: " + rawStep);
    }

    private Step buildStep(String name, int offset) {
        switch (name) {
            case UP:
                return new Up(offset);
            case DOWN:
                return new Down(offset);
            case FORWARD:
                return new Forward(offset);
            default:
                throw new IllegalArgumentException("Unknown step type: " + name);
        }
    }
}
