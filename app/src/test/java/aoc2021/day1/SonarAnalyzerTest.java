package aoc2021.day1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class SonarAnalyzerTest {

    private final SonarAnalyzer sut = new SonarAnalyzer();

    @Test
    public void countNumberOfIncreases() {
        // given
        var measurements = List.of(199L, 200L, 208L, 210L, 200L, 207L, 240L, 269L, 260L, 263L);

        // when
        var count = sut.countNumberOfDepthIncreases(measurements);

        // then
        Assertions.assertEquals(7, count);
    }

}