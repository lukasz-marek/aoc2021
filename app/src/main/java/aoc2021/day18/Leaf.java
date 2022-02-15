package aoc2021.day18;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Leaf leaf = (Leaf) o;
        return value == leaf.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString(){
        return Integer.toString(value);
    }
}
