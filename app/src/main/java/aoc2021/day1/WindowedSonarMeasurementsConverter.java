package aoc2021.day1;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class WindowedSonarMeasurementsConverter implements SonarMeasurementsConverter {
    private final BasicSonarMeasurementsConverter basicSonarMeasurementsConverter;

    public WindowedSonarMeasurementsConverter(BasicSonarMeasurementsConverter basicSonarMeasurementsConverter) {
        this.basicSonarMeasurementsConverter = basicSonarMeasurementsConverter;
    }

    @Override
    public List<Long> convert(Iterable<String> measurements) {
        var basicMeasurements = basicSonarMeasurementsConverter.convert(measurements);
        return convertToWindowed(basicMeasurements);
    }

    private List<Long> convertToWindowed(List<Long> basicMeasurements) {
        return IntStream.range(0, basicMeasurements.size())
                .takeWhile(index -> index + 3 <= basicMeasurements.size()) // alternatively, this could be computer in call to range, but looks more readable like this
                .mapToObj(startIndex -> basicMeasurements.subList(startIndex, startIndex + 3))
                .map(List::stream)
                .map(windowed -> windowed.mapToLong(i -> i))
                .map(LongStream::sum)
                .collect(Collectors.toUnmodifiableList());
    }
}
