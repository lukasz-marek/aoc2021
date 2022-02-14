package aoc2021.day18;

import java.util.function.Consumer;

public class SplittingVisitor implements NodeVisitor<Boolean> {
    private final static NodeVisitor<Leaf> SPLIT_DETECTING_VISITOR = new NodeVisitor<>() {
        @Override
        public Leaf visit(Pair node) {
            return null;
        }

        @Override
        public Leaf visit(Leaf node) {
            return node.getValue() >= 10 ? node : null;
        }
    };

    @Override
    public Boolean visit(Pair node) {
        var leafToSplit = node.getLeft().accept(SPLIT_DETECTING_VISITOR);
        var splitExecuted = executeSplitIfNonNull(leafToSplit, node::setLeft);

        if (!splitExecuted) splitExecuted = node.getLeft().accept(this);

        if (!splitExecuted) {
            leafToSplit = node.getRight().accept(SPLIT_DETECTING_VISITOR);
            splitExecuted = executeSplitIfNonNull(leafToSplit, node::setRight);
        }

        if (!splitExecuted) splitExecuted = node.getRight().accept(this);

        return splitExecuted;
    }

    @Override
    public Boolean visit(Leaf node) {
        return false;
    }

    private boolean executeSplitIfNonNull(Leaf leaf, Consumer<Pair> valueSetter) {
        if (leaf == null) return false;
        var newLeft = Math.floorDiv(leaf.getValue(), 2);
        var newRight = leaf.getValue() - newLeft;
        valueSetter.accept(Pair.of(newLeft, newRight));
        return true;
    }
}
