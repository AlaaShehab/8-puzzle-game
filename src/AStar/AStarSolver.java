package AStar;

import Utils.Node;
import Utils.State;

import java.awt.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.function.Function;

public class AStarSolver {
    private String puzzle;
    private static String goalPuzzle = "012345678";
    private static final int DIM = 3;
    private static final int DIR = 4;
    private static final int[] DELTAS = {1, -1, 0, 0};
    private int maxDepth;
    private Node result;
    private int nodesExpanded = 0;

    public AStarSolver(String puzzle) {
        this.puzzle = puzzle;
        nodesExpanded = 0;
    }

    public boolean solve(Function<String, Integer> function) {
        PriorityQueue<Node> fringe = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node node, Node t1) {
                return Integer.compare(node.getCost(), t1.getCost());
            }
        });

        int zeroPosition = -1;
        for (int i = 0; i < puzzle.length(); i++) {
            if (puzzle.charAt(i) == '0') {
                zeroPosition = i;
                break;
            }
        }
        if (zeroPosition == -1) {
            System.out.println("invalid puzzle state, blank block wasn't found");
            return false;
        }

        Node startNode = new Node(new State(puzzle, zeroPosition));
        startNode.setCost(0);
        startNode.setDepth(0);
        fringe.add(startNode);
        Set<String> visited = new HashSet<>();
        Set<String> explored = new HashSet<>();
        while (!fringe.isEmpty()) {

            Node currentNode = fringe.poll();
            if (explored.contains(currentNode.getState().getCurrentPuzzle()))
                continue;
            State currentState = currentNode.getState();
            explored.add(currentState.getCurrentPuzzle());

            nodesExpanded++;

            int currentDepth = currentNode.getDepth();
            if (maxDepth < currentDepth) {
                maxDepth = currentDepth;
            }
            //TODO if current state not solvable return false

            if (currentState.getCurrentPuzzle().equals(goalPuzzle)) {
                result = currentNode;
                return true;
            }
            Point zeroCoordinatePosition = new Point(
                    currentState.getZeroPosition() / DIM,
                    currentState.getZeroPosition() % DIM);

            for (int i = 0; i < DIR; i++) {
                int newX = (zeroCoordinatePosition.x + DELTAS[i]);
                int newY = (zeroCoordinatePosition.y + DELTAS[DIR - i - 1]);
                if(newX < 0 || newX >= DIM || newY < 0 || newY >= DIM)
                    continue;
                int newZeroPosition = newX * DIM + newY;

                State newState = currentState.swapPositions(newZeroPosition);
                Node newNode = new Node(newState);
                newNode.setParent(currentNode);
                currentNode.addChild(newNode);
                newNode.setDepth(currentNode.getDepth() + 1);
                int cost_estimate = function.apply(newState.getCurrentPuzzle());
                newNode.setCost(newState.getCost() + cost_estimate);
                fringe.add(newNode);
                visited.add(currentState.getCurrentPuzzle());
            }
        }
        return false;
    }

    public Node getResult() { return result; }

    public int getMaxDepth() { return maxDepth; }

    public int getNodesExpanded() { return nodesExpanded; }


}
