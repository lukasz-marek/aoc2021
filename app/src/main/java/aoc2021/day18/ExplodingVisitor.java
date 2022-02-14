package aoc2021.day18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
            // TODO
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
