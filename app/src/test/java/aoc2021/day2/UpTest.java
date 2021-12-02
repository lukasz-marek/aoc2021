package aoc2021.day2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UpTest {

    @Test
    public void takeReturnsCorrectValue() {
        // given
        var currentPosition = new Position(12, 17);
        var sut = new Up(3);

        // when
        var nextPosition = sut.take(currentPosition);

        // then
        Assertions.assertEquals(14, nextPosition.getDepth());
        Assertions.assertEquals(12, nextPosition.getHorizontal());
    }

    @Test
    public void takeAimReturnsCorrectValue() {
        // given
        var currentPosition = new AimedPosition(2, 3, 5);
        var sut = new Up(3);

        // when
        var nextPosition = sut.take(currentPosition);

        // then
        Assertions.assertEquals(3, nextPosition.getDepth());
        Assertions.assertEquals(2, nextPosition.getHorizontal());
        Assertions.assertEquals(2, nextPosition.getAim());
    }
}