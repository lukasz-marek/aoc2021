package aoc2021.day18;

public class MagnitudeComputingVisitor implements NodeVisitor<Long> {
    @Override
    public Long visit(Pair node) {
        var left = node.getLeft().accept(this);
        var right = node.getRight().accept(this);
        return 3 * left + 2 * right;
    }

    @Override
    public Long visit(Leaf node) {
        return (long) node.getValue();
    }
}
