package aoc2021.day1;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SonarMeasurementsConverter {
    private final static boolean USE_PARALLEL_STREAM = false;

    public List<Long> convert(Iterable<String> measurements) {
        return StreamSupport.stream(measurements.spliterator(), USE_PARALLEL_STREAM)
                .map(Long::valueOf)
                .collect(Collectors.toUnmodifiableList());
    }
}
