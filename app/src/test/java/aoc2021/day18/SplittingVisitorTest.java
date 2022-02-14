package aoc2021.day18;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

class SplittingVisitorTest {

    private final SplittingVisitor tested = new SplittingVisitor();

    public static List<Arguments> leafsAndNodes() {
        return List.of(Arguments.of(Leaf.of(10), Pair.of(5, 5)), Arguments.of(Leaf.of(11), Pair.of(5, 6)), Arguments.of(Leaf.of(12), Pair.of(6, 6)));
    }

    @ParameterizedTest
    @MethodSource("leafsAndNodes")
    public void splitsLeafNodeOnTheLeft(Leaf leaf, Pair expectedPair) {
        // given
        var node = Pair.of(leaf, Leaf.of(10));

        // when
        var result = node.accept(tested);

        // then
        Assertions.assertTrue(result);
        Assertions.assertEquals(Leaf.of(10), node.getRight());
        Assertions.assertEquals(expectedPair, node.getLeft());
    }

    @ParameterizedTest
    @MethodSource("leafsAndNodes")
    public void splitsLeafNodeOnTheLeftNested(Leaf leaf, Pair expectedPair) {
        // given
        var node = Pair.of(Pair.of(leaf, Leaf.of(5)), Leaf.of(10));

        // when
        var result = node.accept(tested);

        // then
        Assertions.assertTrue(result);
        Assertions.assertEquals(Leaf.of(10), node.getRight());
        Assertions.assertEquals(Pair.of(expectedPair, Leaf.of(5)), node.getLeft());
    }

    @ParameterizedTest
    @MethodSource("leafsAndNodes")
    public void splitsLeafNodeOnTheRightNested(Leaf leaf, Pair expectedPair) {
        // given
        var node = Pair.of(Leaf.of(9), Pair.of(Leaf.of(5), leaf));

        // when
        var result = node.accept(tested);

        // then
        Assertions.assertTrue(result);
        Assertions.assertEquals(Leaf.of(9), node.getLeft());
        Assertions.assertEquals(Pair.of(Leaf.of(5), expectedPair), node.getRight());
    }

    @ParameterizedTest
    @MethodSource("leafsAndNodes")
    public void splitsLeafNodeInTheMiddleNested(Leaf leaf, Pair expectedPair) {
        // given
        var node = Pair.of(Leaf.of(9), Pair.of(leaf, Leaf.of(5)));

        // when
        var result = node.accept(tested);

        // then
        Assertions.assertTrue(result);
        Assertions.assertEquals(Leaf.of(9), node.getLeft());
        Assertions.assertEquals(Pair.of(expectedPair, Leaf.of(5)), node.getRight());
    }

    @ParameterizedTest
    @MethodSource("leafsAndNodes")
    public void splitsLeafNodeOnTheRight(Leaf leaf, Pair expectedPair) {
        // given
        var node = Pair.of(Leaf.of(9), leaf);

        // when
        var result = node.accept(tested);

        // then
        Assertions.assertTrue(result);
        Assertions.assertEquals(Leaf.of(9), node.getLeft());
        Assertions.assertEquals(expectedPair, node.getRight());
    }

    @Test
    public void leavesLeafUntouchedWhenValueBelowTen() {
        // given
        var node = Pair.of(Leaf.of(9), Leaf.of(9));

        // when
        var result = node.accept(tested);

        // then
        Assertions.assertFalse(result);
        Assertions.assertEquals(Leaf.of(9), node.getLeft());
        Assertions.assertEquals(Leaf.of(9), node.getRight());
    }
}