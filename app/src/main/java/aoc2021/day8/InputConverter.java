package aoc2021.day8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputConverter {
    public List<SegmentIO> convert(List<String> input) {
        return input.stream().map(this::convert).collect(Collectors.toUnmodifiableList());
    }

    private SegmentIO convert(String input) {
        var parts = input.split("\\|");
        var inputs = parts[0];
        var outputs = parts[1];
        var convertedInputs = Arrays.stream(inputs.split(" "))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(this::convertToInput)
                .collect(Collectors.toUnmodifiableList());
        var convertedOutputs = Arrays.stream(outputs.split(" "))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(this::convertToOutput)
                .collect(Collectors.toUnmodifiableList());
        return new SegmentIO(convertedInputs, convertedOutputs);
    }

    private DisplayInput convertToInput(String input) {
        var signals = input.chars().mapToObj(i -> (char) i).collect(Collectors.toUnmodifiableSet());
        return new DisplayInput(signals);
    }

    private DisplayOutput convertToOutput(String input) {
        var signals = input.chars().mapToObj(i -> (char) i).collect(Collectors.toUnmodifiableSet());
        return new DisplayOutput(signals);
    }
}
