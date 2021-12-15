package aoc2021.day13;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FoldHorizontallyTest {

    @Test
    public void keepEmptySpaces() {
        // given
        char[][] paper = {
                {'-'},
                {'-'},
                {'-'},
                {'-'},
                {'-'}
        };
        var instruction = new FoldHorizontally(2);

        // when
        var folded = instruction.perform(paper);

        // then
        char[][] expected = {
                {'-'},
                {'-'},
        };
        Assertions.assertArrayEquals(expected, folded);
    }

    @Test
    public void moveFromBottomToTop1() {
        // given
        char[][] paper = {
                {'-'},
                {'-'},
                {'-'},
                {'-'},
                {'#'}
        };
        var instruction = new FoldHorizontally(2);

        // when
        var folded = instruction.perform(paper);

        // then
        char[][] expected = {
                {'#'},
                {'-'},
        };
        Assertions.assertArrayEquals(expected, folded);
    }

    @Test
    public void moveFromBottomToTop2() {
        // given
        char[][] paper = {
                {'#'},
                {'-'},
                {'-'},
                {'#'},
                {'-'}
        };
        var instruction = new FoldHorizontally(2);

        // when
        var folded = instruction.perform(paper);

        // then
        char[][] expected = {
                {'#'},
                {'#'},
        };
        Assertions.assertArrayEquals(expected, folded);
    }

    @Test
    public void mergeOverlappingDots() {
        // given
        char[][] paper = {
                {'#'},
                {'-'},
                {'-'},
                {'-'},
                {'#'}
        };
        var instruction = new FoldHorizontally(2);

        // when
        var folded = instruction.perform(paper);

        // then
        char[][] expected = {
                {'#'},
                {'-'},
        };
        Assertions.assertArrayEquals(expected, folded);
    }
}