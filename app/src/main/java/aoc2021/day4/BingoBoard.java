package aoc2021.day4;

import com.google.common.primitives.Booleans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BingoBoard {
    private final int[][] board;
    private final boolean[][] marked;
    private final List<Integer> moves;

    public BingoBoard(int[][] board) {
        this.moves = new ArrayList<>();
        this.board = board;
        marked = new boolean[board.length][];
        for (var i = 0; i < board.length; i++)
            marked[i] = new boolean[board[i].length]; // defaults to false for each element
        validateColumns();
    }

    public void mark(int value) {
        for (var row = 0; row < board.length; row++)
            for (var column = 0; column < board[row].length; column++)
                if (board[row][column] == value)
                    mark(row, column, value);
    }

    public boolean isComplete() {
        return isAnyRowComplete() || isAnyColumnComplete();
    }

    public int calculateScore() {
        var sum = 0;

        for (var i = 0; i < board.length; i++)
            for (var j = 0; j < board[i].length; j++)
                if (!marked[i][j])
                    sum += board[i][j];

        var lastMove = moves.get(moves.size() - 1);
        return sum * lastMove;
    }

    private void validateColumns() {
        var columnsPerRow = Arrays.stream(marked).map(row -> row.length).collect(Collectors.toUnmodifiableList());
        var firstRow = columnsPerRow.get(0);
        for (var row = 1; row < columnsPerRow.size(); row++)
            if (!firstRow.equals(columnsPerRow.get(row)))
                throw new IllegalArgumentException("Number of columns must be constant per board");
    }

    private void mark(int row, int column, int value) {
        marked[row][column] = true;
        moves.add(value);
    }

    private boolean isAnyRowComplete() {
        return isComplete(marked);
    }

    private boolean isAnyColumnComplete() {
        var markedTransposed = swapMarkedRowsAndCols();
        return isComplete(markedTransposed);
    }

    private boolean isComplete(boolean[][] markings) {
        var isRowComplete = false;
        for (var i = 0; i < markings.length && !isRowComplete; i++)
            isRowComplete = Booleans.asList(markings[i]).stream().allMatch(isMarked -> isMarked);
        return isRowComplete;
    }

    private boolean[][] swapMarkedRowsAndCols() {
        var columnSize = marked[0].length; // after validation, we know that number of columns is the same for each row
        var markedTransposed = new boolean[columnSize][];

        for (var i = 0; i < markedTransposed.length; i++)
            markedTransposed[i] = new boolean[marked.length];
        for (var i = 0; i < marked.length; i++)
            for (var j = 0; j < columnSize; j++)
                markedTransposed[j][i] = marked[i][j];

        return markedTransposed;
    }
}
