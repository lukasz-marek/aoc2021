package aoc2021.day2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DownTest {

    @Test
    public void takeReturnsCorrectValue() {
        // given
        var currentPosition = new Position(12, 17);
        var sut = new Down(3);

        // when
        var nextPosition = sut.take(currentPosition);

        // then
        Assertions.assertEquals(20, nextPosition.getDepth());
        Assertions.assertEquals(12, nextPosition.getHorizontal());
    }
}