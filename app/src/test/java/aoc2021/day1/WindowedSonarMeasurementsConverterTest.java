package aoc2021.day1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class WindowedSonarMeasurementsConverterTest {
    private final WindowedSonarMeasurementsConverter sut =
            new WindowedSonarMeasurementsConverter(new BasicSonarMeasurementsConverter());

    @Test
    public void generateValidWindowedMeasurements() {
        // given
        var measurements = List.of("199", "200", "208", "210", "200", "207", "240", "269", "260", "263");

        // when
        var converted = sut.convert(measurements);

        // then
        Assertions.assertEquals(List.of(607L, 618L, 618L, 617L, 647L, 716L, 769L, 792L), converted);
    }
}