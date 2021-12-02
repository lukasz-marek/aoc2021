package aoc2021.day2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class StepExecutorTest {

    private final StepExecutor sut = new StepExecutor();

    @Test
    public void returnValidPositionAfterSingleStep() {
        // given
        var steps = List.<Step>of(new Forward(7));

        // when
        var position = sut.execute(steps);

        // then
        Assertions.assertEquals(0, position.getDepth());
        Assertions.assertEquals(7, position.getHorizontal());
    }

    @Test
    public void returnValidPositionAfterMultipleSteps() {
        // given
        var steps =
                List.of(new Forward(7), new Forward(5), new Down(8), new Up(7));

        // when
        var position = sut.execute(steps);

        // then
        Assertions.assertEquals(1, position.getDepth());
        Assertions.assertEquals(12, position.getHorizontal());
    }

}