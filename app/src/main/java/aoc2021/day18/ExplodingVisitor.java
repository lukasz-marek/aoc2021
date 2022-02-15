package aoc2021.day18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ExplodingVisitor implements NodeVisitor<Boolean> {

    private static final class PathTrackingVisitor implements NodeVisitor<Boolean> {

        private final List<Pair> path;

        public PathTrackingVisitor(List<Pair> path) {
            this.path = List.copyOf(path);
        }

        @Override
        public Boolean visit(Pair node) {
            if (path.size() < 4) return visitChildren(node);

            executeExplosion(node);
            return true;
        }

        private void executeExplosion(Pair currentNode) {
            getClosestLeafOnLeft(currentNode).ifPresent(leaf -> leaf.setValue(leaf.getValue() + ((Leaf) currentNode.getLeft()).getValue()));
            getClosestLeafOnRight(currentNode).ifPresent(leaf -> leaf.setValue(leaf.getValue() + ((Leaf) currentNode.getRight()).getValue()));
            updateParent(currentNode);
        }

        private Optional<Leaf> getClosestLeafOnLeft(Pair currentNode) {
            var nodes = getNodesFromLeftToRight();
            var leavesOnLeft = nodes.stream()
                    .takeWhile(node -> node != currentNode)
                    .filter(node -> node instanceof Leaf)
                    .filter(node -> node != currentNode.getLeft())
                    .toList();

            if (leavesOnLeft.isEmpty()) return Optional.empty();

            return Optional.of((Leaf) leavesOnLeft.get(leavesOnLeft.size() - 1));
        }

        private Optional<Leaf> getClosestLeafOnRight(Pair currentNode) {
            var nodes = getNodesFromLeftToRight();
            var leavesOnRight = nodes.stream()
                    .dropWhile(node -> node != currentNode)
                    .dropWhile(node -> node == currentNode)
                    .filter(node -> node instanceof Leaf)
                    .filter(node -> node != currentNode.getRight())
                    .toList();

            if (leavesOnRight.isEmpty()) return Optional.empty();

            return Optional.of((Leaf) leavesOnRight.get(0));
        }

        private List<Node> getNodesFromLeftToRight() {
            return path.get(0).accept(NODE_COLLECTOR);
        }

        private void updateParent(Pair currentNode) {
            var parent = path.get(path.size() - 1);
            var onLeft = parent.getLeft() == currentNode;
            if(onLeft)
                parent.setLeft(Leaf.of(0));
            else
                parent.setRight(Leaf.of(0));
        }

        private Boolean visitChildren(Pair node) {
            var newVisitor = new PathTrackingVisitor(extendPath(node));
            return node.getLeft().accept(newVisitor) || node.getRight().accept(newVisitor);
        }

        private List<Pair> extendPath(Pair newNode) {
            var newPath = new ArrayList<>(path);
            newPath.add(newNode);
            return newPath;
        }

        @Override
        public Boolean visit(Leaf node) {
            return false;
        }
    }

    private static final NodeVisitor<List<Node>> NODE_COLLECTOR = new NodeVisitor<>() {
        @Override
        public List<Node> visit(Pair node) {
            var left = node.getLeft().accept(this);
            var right = node.getRight().accept(this);

            var result = new ArrayList<Node>(left.size() + right.size());
            result.addAll(left);
            result.add(node);
            result.addAll(right);
            return result;
        }

        @Override
        public List<Node> visit(Leaf node) {
            return List.of(node);
        }
    };

    @Override
    public Boolean visit(Pair node) {
        return node.accept(createInitialVisitor());
    }

    @Override
    public Boolean visit(Leaf node) {
        return node.accept(createInitialVisitor());
    }

    private NodeVisitor<Boolean> createInitialVisitor() {
        return new PathTrackingVisitor(Collections.emptyList());
    }
}
