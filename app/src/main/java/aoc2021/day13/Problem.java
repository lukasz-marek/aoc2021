package aoc2021.day13;

import java.util.List;

public class Problem {
    private final List<FoldInstruction> instructions;
    private final char[][] paper;

    public Problem(List<FoldInstruction> instructions, char[][] paper) {
        this.instructions = instructions;
        this.paper = paper;
    }

    public List<FoldInstruction> getInstructions() {
        return instructions;
    }

    public char[][] getPaper() {
        return paper;
    }
}
