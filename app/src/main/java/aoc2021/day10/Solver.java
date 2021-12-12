package aoc2021.day10;

import aoc2021.common.InputLoader;

public class Solver {
    private final InputLoader inputLoader;
    private final InputConverter inputConverter;
    private final CorruptionDetector corruptionDetector;
    private final ErrorScorer errorScorer;

    public Solver() {
        inputLoader = new InputLoader();
        inputConverter = new InputConverter();
        corruptionDetector = new CorruptionDetector();
        errorScorer = new ErrorScorer();
    }

    public int solvePart1() {
        var rawData = inputLoader.loadInput("day10_1.txt");
        var chunks = inputConverter.convert(rawData);
        var invalidTokens = corruptionDetector.detectCorruptions(chunks);
        var errorScores = errorScorer.score(invalidTokens);
        return errorScores.stream().mapToInt(Integer::intValue).sum();
    }
}
