package aoc2021.day4;

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
}
