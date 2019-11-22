package BFS;

import Utils.Node;
import Utils.State;

import java.awt.*;
import java.util.*;

public class BFSSolver {
    private static int DIM = 3;
    private static int DIR = 4;
    private static int INVERSION_COST = 1;
    private String puzzle;
    private static String solvedPuzzle = "012345678";
    private static int[] row = {0,0,1,-1};
    private static int[] col = {1,-1,0,0};

    private int nodesExpanded;
    private long runningTime;
    private int maxDepth;

    private Node result;

    public BFSSolver (String puzzle) {
        this.puzzle = puzzle;
        nodesExpanded = 0;
    }

    public boolean solve () {
        Queue<Node> fringe = new LinkedList<>();

        int zeroPosition = -1;
        for (int i = 0; i < puzzle.length(); i++) {
            if (puzzle.charAt(i) == '0') {
                zeroPosition = i;
            }
        }
        if (zeroPosition == -1) {
            System.out.println("The zero is not found! Wrong puzzle input");
            return false;
        }

        long start = System.currentTimeMillis();

        State startState = new State(puzzle, zeroPosition);
        Set<String> visited = new HashSet<>();
        Node root = new Node(startState);
        fringe.add(root);

        while (!fringe.isEmpty()) {
            Node currentNode = fringe.peek();
            State currentState = new State();
            currentState.setZeroPosition(fringe.peek().getState().getZeroPosition());
            currentState.setCurrentPuzzle(fringe.poll().getState().getCurrentPuzzle());
            nodesExpanded++;

            visited.add(currentState.getCurrentPuzzle());
            if ((currentState.getCurrentPuzzle()).equals(solvedPuzzle)) {
                result = currentNode;
                runningTime = System.currentTimeMillis() - start;
                return true;
            }
            //x is the row and y is the column
            Point zeroPositionIn2dFormat = new Point(
                    (currentState.getZeroPosition())/DIM,
                    (currentState.getZeroPosition())%DIM);

            for (int i = 0; i < DIR; i++) {
                int newDirection = ((zeroPositionIn2dFormat.x + row[i]) * DIM)
                        + (zeroPositionIn2dFormat.y + col[i]);
                if ((zeroPositionIn2dFormat.x + row[i]) >= DIM || (zeroPositionIn2dFormat.x + row[i]) < 0
                || (zeroPositionIn2dFormat.y + col[i]) >= DIM || (zeroPositionIn2dFormat.y + col[i]) < 0) {
                    continue;
                }
                State newState = swapPositions(currentState, newDirection);
                if (visited.contains(newState.getCurrentPuzzle())) {
                    continue;
                }
                Node newNode = new Node(newState);
                newNode.setParent(currentNode);
                newNode.setCost(currentNode.getCost() + INVERSION_COST);
                newNode.setDepth(currentNode.getDepth() + 1);
                currentNode.addChild(newNode);

                fringe.add(newNode);
            }
        }
        return false;
    }
    public Node getResult () {
        return result;
    }

    private State swapPositions(State currentState, int newDirection) {
        State newState = new State();
        char[] newPuzzle = (currentState.getCurrentPuzzle()).toCharArray();

        char temp = newPuzzle[currentState.getZeroPosition()];
        newPuzzle[currentState.getZeroPosition()] = newPuzzle[newDirection];
        newPuzzle[newDirection] = temp;
        newState.setCurrentPuzzle(String.valueOf(newPuzzle));
        newState.setZeroPosition(newDirection);
        return newState;
    }

    public int getNodesExpanded() {
        return nodesExpanded;
    }

    public void setRunningTime(long runningTime) {
        this.runningTime = runningTime;
    }

    public long getRunningTime() {
        return runningTime;
    }

    public int getMaxDepth() {
        return result.getDepth();
    }
}


