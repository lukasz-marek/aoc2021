package aoc2021.day2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ForwardTest {

    @Test
    public void takeReturnsCorrectValue() {
        // given
        var currentPosition = new Position(12, 17);
        var sut = new Forward(3);

        // when
        var nextPosition = sut.take(currentPosition);

        // then
        Assertions.assertEquals(17, nextPosition.getDepth());
        Assertions.assertEquals(15, nextPosition.getHorizontal());
    }

    @Test
    public void takeAimedReturnsCorrectValue() {
        // given
        var currentPosition = new AimedPosition(2, 3, 4);
        var sut = new Forward(3);

        // when
        var nextPosition = sut.take(currentPosition);

        // then
        Assertions.assertEquals(15, nextPosition.getDepth());
        Assertions.assertEquals(5, nextPosition.getHorizontal());
        Assertions.assertEquals(4, nextPosition.getAim());
    }
}