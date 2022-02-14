package aoc2021.day18;

public interface NodeVisitor<T> {
    T visit(Pair node);
    T visit(Leaf node);
}
