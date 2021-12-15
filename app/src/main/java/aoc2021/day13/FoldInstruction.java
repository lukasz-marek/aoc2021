package aoc2021.day13;

@FunctionalInterface
public interface FoldInstruction {
    char[][] perform(char[][] paper);
}
