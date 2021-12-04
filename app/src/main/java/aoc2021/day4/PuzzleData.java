package aoc2021.day4;

import java.util.List;

public class PuzzleData {
    private final List<Integer> moves;
    private final List<BingoBoard> boards;

    public PuzzleData(List<Integer> moves, List<BingoBoard> boards) {
        this.moves = moves;
        this.boards = boards;
    }

    public List<Integer> getMoves() {
        return moves;
    }

    public List<BingoBoard> getBoards() {
        return boards;
    }
}
