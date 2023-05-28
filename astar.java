import java.util.*;

public class astar {
    private static class Node {
        private int x;
        private int y;
        private int fScore;
        private int gScore;
        private Node parent;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.fScore = Integer.MAX_VALUE;
            this.gScore = Integer.MAX_VALUE;
            this.parent = null;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getFScore() {
            return fScore;
        }

        public int getGScore() {
            return gScore;
        }

        public Node getParent() {
            return parent;
        }

        public void setGScore(int gScore) {
            this.gScore = gScore;
        }

        public void setFScore(int fScore) {
            this.fScore = fScore;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }
    }

    private static List<Node> findPath(Node start, Node goal) {
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(Node::getFScore));
        Set<Node> closedSet = new HashSet<>();

        start.setGScore(0);
        start.setFScore(calculateFScore(start, goal));
        openSet.add(start);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.equals(goal)) {
                return reconstructPath(current);
            }

            closedSet.add(current);

            // Explore neighboring nodes
            for (Node neighbor : getNeighbors(current)) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                int tentativeGScore = current.getGScore() + 1;

                if (!openSet.contains(neighbor) || tentativeGScore < neighbor.getGScore()) {
                    neighbor.setParent(current);
                    neighbor.setGScore(tentativeGScore);
                    neighbor.setFScore(tentativeGScore + calculateFScore(neighbor, goal));

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        return null; // No path found
    }

    private static List<Node> reconstructPath(Node current) {
        List<Node> path = new ArrayList<>();

        while (current != null) {
            path.add(current);
            current = current.getParent();
        }

        Collections.reverse(path);
        return path;
    }

    private static int calculateFScore(Node node, Node goal) {
        // Calculate Manhattan distance as heuristic
        return Math.abs(node.getX() - goal.getX()) + Math.abs(node.getY() - goal.getY());
    }

    private static List<Node> getNeighbors(Node node) {
        int x = node.getX();
        int y = node.getY();

        List<Node> neighbors = new ArrayList<>();
        // Consider adjacent nodes (up, down, left, right)
        neighbors.add(new Node(x - 1, y));
        neighbors.add(new Node(x + 1, y));
        neighbors.add(new Node(x, y - 1));
        neighbors.add(new Node(x, y + 1));

        return neighbors;
    }

    public static void main(String[] args) {
        Node start = new Node(0, 0);
        Node goal = new Node(4, 4);

        List<Node> path = findPath(start, goal);

        if (path != null) {
            System.out.println("Path found:");
           
        }}