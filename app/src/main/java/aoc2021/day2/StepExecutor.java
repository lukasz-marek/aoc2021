package aoc2021.day2;

import java.util.List;

public class StepExecutor {
    private final Position initialPosition = new Position();

    public Position execute(List<Step> steps) {
        var position = initialPosition;
        for (var step : steps)
            position = step.take(position);
        return position;
    }
}
