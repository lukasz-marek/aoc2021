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

    @Test
    public void explodesInTheMiddle() {
        // given
        var node = Pair.of(Pair.of(Pair.of(Pair.of(Leaf.of(6), Pair.of(4, 5)), Leaf.of(6)), Leaf.of(6)), Leaf.of(6));

        // when
        var result = node.accept(tested);

        // then
        Assertions.assertTrue(result);
        var expected = Pair.of(Pair.of(Pair.of(Pair.of(Leaf.of(10), Leaf.of(0)), Leaf.of(11)), Leaf.of(6)), Leaf.of(6));
        Assertions.assertEquals(expected, node);
    }

    @Test
    public void explodesOnRight() {
        // given
        var node = Pair.of(Leaf.of(1), Pair.of(Leaf.of(2), Pair.of(Leaf.of(3), Pair.of(Leaf.of(4), Pair.of(4, 5)))));

        // when
        var result = node.accept(tested);

        // then
        Assertions.assertTrue(result);
        var expected = Pair.of(Leaf.of(1), Pair.of(Leaf.of(2), Pair.of(Leaf.of(3), Pair.of(Leaf.of(8), Leaf.of(0)))));
        Assertions.assertEquals(expected, node);
    }

    @Test
    public void doesNothingWhenNestedTooShallowly() {
        // given
        var node = Pair.of(Leaf.of(1), Pair.of(Leaf.of(2), Pair.of(Leaf.of(3), Leaf.of(6))));

        // when
        var result = node.accept(tested);

        // then
        Assertions.assertFalse(result);
        var expected = Pair.of(Leaf.of(1), Pair.of(Leaf.of(2), Pair.of(Leaf.of(3), Leaf.of(6))));
        Assertions.assertEquals(expected, node);
    }

}