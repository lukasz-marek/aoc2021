package aoc2021.day6;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LanternfishSimulator {
    public Map<Lanternfish, Long> nextStep(Map<Lanternfish, Long> currentStep) {
        var nextState = new HashMap<Lanternfish, Long>();
        for (var lanternfishByTimer : currentStep.entrySet())
            processLanternfish(lanternfishByTimer.getKey(), lanternfishByTimer.getValue(), nextState);
        return Collections.unmodifiableMap(nextState);
    }

    private void processLanternfish(Lanternfish lanternfish, long count, Map<Lanternfish, Long> nextState) {
        if (lanternfish.getTimer() == 0)
            createNewLanternfishes(count, nextState);

        var nextTimer = lanternfish.getTimer() - 1;
        if (nextTimer < 0)
            nextTimer = 6;

        var newFish = new Lanternfish(nextTimer);
        var currentCount = nextState.getOrDefault(newFish, 0L);
        nextState.put(newFish, currentCount + count);
    }

    private void createNewLanternfishes(long count, Map<Lanternfish, Long> nextState) {
        var lanternfish = new Lanternfish(8);
        var currentNumber = nextState.getOrDefault(lanternfish, 0L);
        nextState.put(lanternfish, currentNumber + count);
    }
}
