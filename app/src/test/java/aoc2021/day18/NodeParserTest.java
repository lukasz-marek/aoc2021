package aoc2021.day18;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NodeParserTest {

    private final NodeParser tested = new NodeParser();

    @Test
    public void parsesTwoNumbers() {
        // given
        var input = "[7,15]";

        //when
        var result = tested.parse(input);

        // then
        Assertions.assertEquals(Pair.of(7, 15), result);
    }

    @Test
    public void parseNumberOnLeft() {
        // given
        var input = "[5,[10,9]]";

        //when
        var result = tested.parse(input);

        // then
        Assertions.assertEquals(Pair.of(Leaf.of(5), Pair.of(10, 9)), result);
    }

    @Test
    public void parseNumberOnRight() {
        // given
        var input = "[[10,9],5]";

        //when
        var result = tested.parse(input);

        // then
        Assertions.assertEquals(Pair.of(Pair.of(10, 9), Leaf.of(5)), result);
    }

    @Test
    public void parsePairOnBothSides() {
        // given
        var input = "[[10,9],[5,6]]";

        //when
        var result = tested.parse(input);

        // then
        Assertions.assertEquals(Pair.of(Pair.of(10, 9), Pair.of(5, 6)), result);
    }

}