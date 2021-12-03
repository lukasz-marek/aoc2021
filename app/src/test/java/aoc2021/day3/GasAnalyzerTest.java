package aoc2021.day3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

class GasAnalyzerTest {

    private final GasAnalyzer sut = new GasAnalyzer();

    @Test
    public void returnCorrectValue() {
        // given
        var reports =
                Stream.of("00100", "11110", "10110", "10111", "10101", "01111", "00111", "11100", "10000", "11001", "00010", "01010")
                        .map(ReportEntry::new)
                        .collect(Collectors.toUnmodifiableList());
        // when
        var result = sut.analyze(reports);

        // then
        Assertions.assertEquals(23, result.getGeneratorRating());
        Assertions.assertEquals(10, result.getScrubberRating());
    }
}