package aoc2021.day18;

public interface Node {
    <T> T accept(NodeVisitor<T> visitor);
}
