package aoc2021.day6;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputConverter {
    public List<Lanternfish> convert(List<String> input) {
        var rawData = input.get(0);
        return Arrays.stream(rawData.split(","))
                .map(String::trim)
                .map(Integer::valueOf)
                .map(Lanternfish::new)
                .collect(Collectors.toUnmodifiableList());
    }
}
