package aoc2021.day4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class BingoBoardTest {

    @Test
    public void shouldDetectCompleteGame() {
        // given
        var moves = List.of(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24);
        int[][] boardContent = {
                {14, 21, 17, 24, 4},
                {10, 16, 15, 9, 19},
                {18, 8, 23, 26, 20},
                {22, 11, 13, 6, 5},
                {2, 0, 12, 3, 7}
        };
        var sut = new BingoBoard(boardContent);

        // when
        for (var move : moves)
            sut.mark(move);

        // then
        Assertions.assertTrue(sut.isComplete());
    }

    @Test
    public void shouldDetectInCompleteGame() {
        // given
        var moves = List.of(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21);
        int[][] boardContent = {
                {14, 21, 17, 24, 4},
                {10, 16, 15, 9, 19},
                {18, 8, 23, 26, 20},
                {22, 11, 13, 6, 5},
                {2, 0, 12, 3, 7}
        };
        var sut = new BingoBoard(boardContent);

        // when
        for (var move : moves)
            sut.mark(move);

        // then
        Assertions.assertFalse(sut.isComplete());
    }

    @Test
    public void shouldReturnCorrectScore() {
        // given
        var moves = List.of(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24);
        int[][] boardContent = {
                {14, 21, 17, 24, 4},
                {10, 16, 15, 9, 19},
                {18, 8, 23, 26, 20},
                {22, 11, 13, 6, 5},
                {2, 0, 12, 3, 7}
        };
        var sut = new BingoBoard(boardContent);

        // when
        for (var move : moves)
            sut.mark(move);

        // then
        Assertions.assertEquals(4512, sut.calculateScore());
    }
}