package aoc2021.day4;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputConverter {
    public PuzzleData convert(List<String> input) {
        var moves = parseMoves(input.get(0));
        var boards = parseBoards(input.subList(1, input.size()));
        return new PuzzleData(moves, boards);
    }

    private List<Integer> parseMoves(String input) {
        return Arrays.stream(input.trim().split(","))
                .map(String::trim)
                .map(Integer::valueOf)
                .collect(Collectors.toUnmodifiableList());
    }

    private List<BingoBoard> parseBoards(List<String> input) {
        var parts = Lists.partition(input, 6);
        return parts.stream().map(this::parseBoard).collect(Collectors.toUnmodifiableList());
    }

    private BingoBoard parseBoard(List<String> rawData) {
        var boardData = new int[rawData.size() - 1][];
        for (var row = 1; row < rawData.size(); row++) { // skip initial empty line
            boardData[row - 1] = Arrays.stream(rawData.get(row).split(" "))
                    .map(String::trim)
                    .filter(string -> !string.isEmpty())
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        return new BingoBoard(boardData);
    }
}
