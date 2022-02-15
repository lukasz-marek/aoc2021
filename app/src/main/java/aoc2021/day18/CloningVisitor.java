package aoc2021.day18;

public class CloningVisitor implements NodeVisitor<Node> {
    @Override
    public Node visit(Pair node) {
        var leftClone = node.getLeft().accept(this);
        var rightClone = node.getRight().accept(this);
        return Pair.of(leftClone, rightClone);
    }

    @Override
    public Node visit(Leaf node) {
        return Leaf.of(node.getValue());
    }
}
