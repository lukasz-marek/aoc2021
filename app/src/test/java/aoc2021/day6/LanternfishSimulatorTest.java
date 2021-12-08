package aoc2021.day6;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LanternfishSimulatorTest {

    private final LanternfishSimulator sut = new LanternfishSimulator();

    @Test
    public void decrementsTimers() {
        // given
        var state = new HashMap<Lanternfish, Long>();
        state.put(new Lanternfish(8), 1L);
        state.put(new Lanternfish(1), 2L);

        // when
        var nextState = sut.nextStep(state);

        // then
        assertEquals(2, nextState.size());
        assertEquals(1L, nextState.get(new Lanternfish(7)));
        assertEquals(2L, nextState.get(new Lanternfish(0)));
    }

    @Test
    public void createsNewLanternfish() {
        // given
        var state = new HashMap<Lanternfish, Long>();
        state.put(new Lanternfish(0), 4L);

        // when
        var nextState = sut.nextStep(state);

        // then
        assertEquals(2, nextState.size());
        assertEquals(4L, nextState.get(new Lanternfish(8)));
        assertEquals(4L, nextState.get(new Lanternfish(6)));
    }

    @Test
    public void createsNewLanternfishAndDecrementsTimers() {
        // given
        var state = new HashMap<Lanternfish, Long>();
        state.put(new Lanternfish(0), 4L);
        state.put(new Lanternfish(1), 2L);

        // when
        var nextState = sut.nextStep(state);

        // then
        assertEquals(3, nextState.size());
        assertEquals(4L, nextState.get(new Lanternfish(8)));
        assertEquals(4L, nextState.get(new Lanternfish(6)));
        assertEquals(2L, nextState.get(new Lanternfish(0)));
    }

    @Test
    public void mergeOldAndNewLanternfish() {
        // given
        var state = new HashMap<Lanternfish, Long>();
        state.put(new Lanternfish(0), 3L);
        state.put(new Lanternfish(7), 2L);

        // when
        var nextState = sut.nextStep(state);

        // then
        assertEquals(2, nextState.size());
        assertEquals(3L, nextState.get(new Lanternfish(8)));
        assertEquals(5L, nextState.get(new Lanternfish(6)));
    }
}