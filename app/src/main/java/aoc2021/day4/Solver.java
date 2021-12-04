package aoc2021.day4;

import aoc2021.common.InputLoader;

public class Solver {
    private final InputLoader inputLoader;
    private final InputConverter inputConverter;
    private final BingoExecutor bingoExecutor;

    public Solver(InputLoader inputLoader, InputConverter inputConverter, BingoExecutor bingoExecutor) {
        this.inputLoader = inputLoader;
        this.inputConverter = inputConverter;
        this.bingoExecutor = bingoExecutor;
    }

    public int solve(String inputFile) {
        var input = inputLoader.loadInput(inputFile);
        var puzzleData = inputConverter.convert(input);
        return bingoExecutor.findFirstWinningScore(puzzleData);
    }
}
