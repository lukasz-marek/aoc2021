package aoc2021.day11;

import java.util.List;

public class InputConverter {
    public int[][] convert(List<String> input) {
        int[][] result = new int[input.size()][];
        for (var i = 0; i < input.size(); i++) {
            var numbers = input.get(i).chars()
                    .mapToObj(charcode -> (char) charcode)
                    .mapToInt(character -> Integer.parseInt(String.valueOf(character)))
                    .toArray();
            result[i] = numbers;
        }
        return result;
    }
}
