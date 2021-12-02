package aoc2021.day2;

public final class AimedPosition {
    private final int horizontal;
    private final int depth;
    private final int aim;

    public AimedPosition() {
        this(0, 0, 0);
    }

    public AimedPosition(int horizontal, int depth, int aim) {
        this.horizontal = horizontal;
        this.depth = depth;
        this.aim = aim;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public int getDepth() {
        return depth;
    }

    public int getAim() {
        return aim;
    }
}
