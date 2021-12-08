package aoc2021.day7;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputConverter {
    public List<Integer> convert(List<String> input) {
        var rawData = input.get(0);
        return Arrays.stream(rawData.split(","))
                .map(String::trim)
                .map(Integer::valueOf)
                .collect(Collectors.toUnmodifiableList());
    }
}
