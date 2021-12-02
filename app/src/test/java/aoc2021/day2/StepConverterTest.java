package aoc2021.day2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class StepConverterTest {
    private final StepConverter sut = new StepConverter();

    @Test
    public void convertSingleUp() {
        // given
        var input = List.of("up 7");

        // when
        var result = sut.convert(input);

        // then
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(new Up(7), result.get(0));
    }

    @Test
    public void convertSingleDown() {
        // given
        var input = List.of("down 15");

        // when
        var result = sut.convert(input);

        // then
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(new Down(15), result.get(0));
    }

    @Test
    public void convertSingleForward() {
        // given
        var input = List.of("forward 200");

        // when
        var result = sut.convert(input);

        // then
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(new Forward(200), result.get(0));
    }

    @Test
    public void convertMultipleSteps() {
        // given
        var input = List.of("forward 15", "up 1", "down 3");

        // when
        var result = sut.convert(input);

        // then
        Assertions.assertIterableEquals(List.of(new Forward(15), new Up(1), new Down(3)), result);
    }

}