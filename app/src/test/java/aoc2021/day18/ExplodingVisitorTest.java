package aoc2021.day18;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExplodingVisitorTest {

    private final ExplodingVisitor tested = new ExplodingVisitor();

    @Test
    public void explodesOnLeft() {
        // given
        var node = Pair.of(Pair.of(Pair.of(Pair.of(Pair.of(4, 5), Leaf.of(6)), Leaf.of(6)), Leaf.of(6)), Leaf.of(6));

        // when
        var result = node.accept(tested);

        // then
        Assertions.assertTrue(result);
        var expected = Pair.of(Pair.of(Pair.of(Pair.of(Leaf.of(0), Leaf.of(11)), Leaf.of(6)), Leaf.of(6)), Leaf.of(6));
        Assertions.assertEquals(expected, node);
    }

}