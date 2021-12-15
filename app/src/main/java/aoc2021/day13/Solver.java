package aoc2021.day13;

import aoc2021.common.InputLoader;

import java.util.List;

public class Solver {
    private final InputLoader inputLoader;
    private final InputConverter inputConverter;

    public Solver() {
        inputConverter = new InputConverter();
        inputLoader = new InputLoader();
    }

    public int solvePart1() {
        var rawData = inputLoader.loadInput("day13_1.txt");
        var problemDescription = inputConverter.convert(rawData);
        var folded = problemDescription.getInstructions().get(0).perform(problemDescription.getPaper());
        return countDots(folded);
    }

    private char[][] keepFolding(char[][] paper, List<FoldInstruction> instructions) {
        for (var instruction : instructions)
            paper = instruction.perform(paper);
        return paper;
    }

    private int countDots(char[][] paper) {
        var result = 0;
        for (var row : paper)
            for (var place : row)
                if (place == '#')
                    result++;
        return result;
    }
}
