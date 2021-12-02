package aoc2021.day2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

class StepExecutorTest {

    private final StepExecutor sut = new StepExecutor();

    @Nested
    class PositionTest {

        @Test
        public void returnValidPositionAfterSingleStep() {
            // given
            var steps = List.<Step>of(new Forward(7));
            var initialPosition = new Position();

            // when
            var position = sut.execute(steps, initialPosition);

            // then
            Assertions.assertEquals(0, position.getDepth());
            Assertions.assertEquals(7, position.getHorizontal());
        }

        @Test
        public void returnValidPositionAfterMultipleSteps() {
            // given
            var steps =
                    List.of(new Forward(7), new Forward(5), new Down(8), new Up(7));
            var initialPosition = new Position();

            // when
            var position = sut.execute(steps, initialPosition);

            // then
            Assertions.assertEquals(1, position.getDepth());
            Assertions.assertEquals(12, position.getHorizontal());
        }
    }

    @Nested
    class AimedPositionTest {

        @Test
        public void returnValidAimedPositionAfterSingleStep() {
            // given
            var steps = List.<Step>of(new Forward(7));
            var initialPosition = new AimedPosition();

            // when
            var position = sut.execute(steps, initialPosition);

            // then
            Assertions.assertEquals(0, position.getDepth());
            Assertions.assertEquals(7, position.getHorizontal());
            Assertions.assertEquals(0, position.getAim());
        }

        @Test
        public void returnValidAimedPositionAfterMultipleSteps() {
            // given
            var steps = List.of(new Down(2), new Forward(3));
            var initialPosition = new AimedPosition();

            // when
            var position = sut.execute(steps, initialPosition);

            // then
            Assertions.assertEquals(6, position.getDepth());
            Assertions.assertEquals(3, position.getHorizontal());
            Assertions.assertEquals(2, position.getAim());
        }
    }
}