package aoc2021.day1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class BasicSonarMeasurementsConverterTest {

    private final BasicSonarMeasurementsConverter sut = new BasicSonarMeasurementsConverter();

    @Test
    public void convertListOfIntegerStrings() {
        // given
        var validInput = List.of("123", "456", "888");

        // when
        var converted = sut.convert(validInput);

        // then
        Assertions.assertEquals(List.of(123L, 456L, 888L), converted);
    }
}