package aoc2021.day4;

import java.util.ArrayList;

public class BingoExecutor {
    public int findFirstWinningScore(PuzzleData puzzleData) {
        for (var move : puzzleData.getMoves()) {
            puzzleData.getBoards().forEach(board -> board.mark(move));
            var winner = puzzleData.getBoards().stream().filter(BingoBoard::isComplete).findAny();
            if (winner.isPresent())
                return winner.map(BingoBoard::calculateScore).get();
        }
        throw new IllegalStateException("Failed to find a winner");
    }

    public int findLastWinningScore(PuzzleData puzzleData) {
        var mutableBoards = new ArrayList<>(puzzleData.getBoards());
        var isLast = false;
        for (var move : puzzleData.getMoves()) {
            mutableBoards.forEach(board -> board.mark(move));
            if (!isLast) {
                mutableBoards.removeIf(BingoBoard::isComplete);
                isLast = mutableBoards.size() == 1;
            } else {
                if (mutableBoards.get(0).isComplete())
                    return mutableBoards.get(0).calculateScore();
            }
        }
        throw new IllegalStateException("Failed to find a winner");
    }
}
