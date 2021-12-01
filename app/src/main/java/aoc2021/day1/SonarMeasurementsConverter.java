package aoc2021.day1;

import java.util.List;

public interface SonarMeasurementsConverter {
    List<Long> convert(Iterable<String> measurements);
}