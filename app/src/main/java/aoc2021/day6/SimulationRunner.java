package aoc2021.day6;

import java.util.List;
import java.util.Map;

public class SimulationRunner {
    private final LanternfishSimulator simulator;
    private final DataOptimizer optimizer;

    public SimulationRunner(LanternfishSimulator simulator, DataOptimizer optimizer) {
        this.simulator = simulator;
        this.optimizer = optimizer;
    }

    public Map<Lanternfish, Long> runSimulation(List<Lanternfish> initialState, long steps) {
        var currentState = optimizer.optimize(initialState);
        for (var i = 0L; i < steps; i++)
            currentState = simulator.nextStep(currentState);
        return currentState;
    }
}
