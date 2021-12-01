package aoc2021.day1;

import java.util.List;

public class SonarAnalyzer {
    public int countNumberOfDepthIncreases(List<Long> measurements) {
        var initialMeasurement = measurements.get(0);
        var measurementsToCompare = measurements.subList(1, measurements.size());
        return countIncreases(initialMeasurement, measurementsToCompare);
    }

    private int countIncreases(long initialMeasurement, List<Long> remainingValues) {
        var previousMeasurement = initialMeasurement;
        var count = 0;
        for (long measurement : remainingValues) {
            if (measurement > previousMeasurement)
                count++;
            previousMeasurement = measurement;
        }
        return count;
    }
}
