package aoc2021.day18;

public class Pair implements Node {
    private Node left;
    private Node right;

    private Pair(Node left, Node right){
        this.left = left;
        this.right = right;
    }

    public static Pair of(Node left, Node right){
        return new Pair(left, right);
    }

    public static Pair of(int left, int right){
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
}
