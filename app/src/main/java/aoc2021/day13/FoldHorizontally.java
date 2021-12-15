package aoc2021.day13;

public class FoldHorizontally implements FoldInstruction {
    private final int by;
    private final static char DOT = '#';

    public FoldHorizontally(int by) {
        this.by = by;
    }

    @Override
    public char[][] perform(char[][] paper) {
        var folded = new char[by][];
        for (var i = 0; i < folded.length; i++)
            foldColumn(paper, folded, i);
        return folded;
    }

    private void foldColumn(char[][] paper, char[][] folded, int i) {
        folded[i] = new char[paper[i].length];
        var distanceFromFold = by - i;
        for (var j = 0; j < paper[i].length; j++)
            folded[i][j] = chooseValue(paper[i][j], paper[by + distanceFromFold][j]);
    }

    private char chooseValue(char currentValue, char comingValue) {
        if (currentValue == DOT || comingValue == DOT) return DOT;
        else return '-';
    }
}
