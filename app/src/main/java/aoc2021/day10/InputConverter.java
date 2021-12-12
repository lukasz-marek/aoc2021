package aoc2021.day10;

import java.util.List;
import java.util.stream.Collectors;

public class InputConverter {
    public List<Chunk> convert(List<String> input) {
        return input.stream()
                .map(this::convert)
                .collect(Collectors.toUnmodifiableList());
    }

    private Chunk convert(String input) {
        var tokens = input.chars()
                .mapToObj(character -> new Token((char) character))
                .collect(Collectors.toUnmodifiableList());
        return new Chunk(tokens);
    }
}
