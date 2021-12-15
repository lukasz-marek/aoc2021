package aoc2021.day13;

import java.util.Objects;

public class FoldVertically implements FoldInstruction {
    private final int by;
    private final static char DOT = '#';

    public FoldVertically(int by) {
        this.by = by;
    }

    @Override
    public char[][] perform(char[][] paper) {
        var folded = new char[paper.length][];
        for (var i = 0; i < folded.length; i++)
            folded[i] = foldLine(paper[i]);
        return folded;
    }

    private char[] foldLine(char[] line) {
        Objects.checkIndex(by, line.length);
        var newLine = new char[by];
        for (var i = 0; i < newLine.length; i++) {
            var distanceFromFold = by - i;
            newLine[i] = chooseValue(line[i], line[by + distanceFromFold]);
        }
        return newLine;
    }

    private char chooseValue(char currentValue, char comingValue) {
        if (currentValue == DOT || comingValue == DOT)
            return DOT;
        else return '-';
    }
}
