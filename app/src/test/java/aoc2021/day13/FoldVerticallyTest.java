package aoc2021.day13;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class FoldVerticallyTest {

    @Test
    public void movesDotsWhenNotOverlapping() {
        // given
        char[][] initialPaper = {
                {'#', '-', '-', '#', '-'},
        };
        var instruction = new FoldVertically(2);

        // when
        var folded = instruction.perform(initialPaper);

        // then
        char[][] expected = {{'#', '#'}};
        Assertions.assertArrayEquals(expected, folded);
    }

    @Test
    public void movesDotsWhenOverlapping() {
        // given
        char[][] initialPaper = {
                {'#', '-', '-', '-', '#'},
        };
        var instruction = new FoldVertically(2);

        // when
        var folded = instruction.perform(initialPaper);

        // then
        char[][] expected = {{'#', '-'}};
        Assertions.assertArrayEquals(expected, folded);
    }

    @Test
    public void returnsNoDotsWhenNoDots() {
        // given
        char[][] initialPaper = {
                {'-', '-', '-', '-', '-'},
        };
        var instruction = new FoldVertically(2);

        // when
        var folded = instruction.perform(initialPaper);

        // then
        char[][] expected = {{'-', '-'}};
        Assertions.assertArrayEquals(expected, folded);
    }

    @Test
    public void movesFromRightToLeft() {
        // given
        char[][] initialPaper = {
                {'-', '-', '-', '-', '#'},
        };
        var instruction = new FoldVertically(2);

        // when
        var folded = instruction.perform(initialPaper);

        // then
        char[][] expected = {{'#', '-'}};
        Assertions.assertArrayEquals(expected, folded);
    }
}