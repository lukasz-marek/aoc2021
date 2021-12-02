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
}