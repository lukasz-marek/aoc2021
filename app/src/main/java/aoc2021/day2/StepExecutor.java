package aoc2021.day2;

import java.util.List;

public class StepExecutor {
    public Position execute(List<Step> steps, Position initialPosition) {
        var position = initialPosition;
        for (var step : steps)
            position = step.take(position);
        return position;
    }

    public AimedPosition execute(List<Step> steps, AimedPosition initialPosition) {
        var position = initialPosition;
        for (var step : steps)
            position = step.take(position);
        return position;
    }
}
