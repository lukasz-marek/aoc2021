package aoc2021.day18;

import java.util.Objects;

public class Pair implements Node {
    private Node left;
    private Node right;

    private Pair(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public static Pair of(Node left, Node right) {
        return new Pair(left, right);
    }

    public static Pair of(int left, int right) {
        return Pair.of(Leaf.of(left), Leaf.of(right));
    }

    @Override
    public <T> T accept(NodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return Objects.equals(left, pair.left) && Objects.equals(right, pair.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
