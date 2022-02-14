package aoc2021.day18;

public class Leaf implements Node {
    private int value;

    private Leaf(int value) {
        this.value = value;
    }

    public static Leaf of(int value) {
        return new Leaf(value);
    }

    @Override
    public <T> T accept(NodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
