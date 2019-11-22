package DFS;
import Utils.Node;
import Utils.State;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class DFSSolver {
    private static int DIM = 3;
    private static int DIR = 4;
    private String puzzle;
    private static String solvedPuzzle = "012345678";
    private static int[] row = {0,0,1,-1};
    private static int[] col = {1,-1,0,0};
    private Node path;
    private int maxDepth;


    /*Constructor*/
    public DFSSolver(String puzzle) {
        this.puzzle = puzzle;
    }

    /*Move a tile to a new direction*/
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

    public boolean solve() {
        Stack<Node> fringe = new Stack<>();
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

        State startState = new State(puzzle, zeroPosition);
        Set<String> visited = new HashSet<>();
        Node root = new Node(startState);
        //TODO depth 0 or 1 ?
        root.setDepth(1);
        root.setCost(0);
        fringe.push(root);

        while (!fringe.isEmpty()) {
            Node currentNode = fringe.pop();
            State currentState = new State();
            currentState.setZeroPosition(currentNode.getState().getZeroPosition());
            currentState.setCurrentPuzzle(currentNode.getState().getCurrentPuzzle());
            int currDepth = currentNode.getDepth();
            if(maxDepth < currDepth) {
                maxDepth = currDepth;
            }
            visited.add(currentState.getCurrentPuzzle());
            if ((currentState.getCurrentPuzzle()).equals(solvedPuzzle)) {
                path = currentNode;
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
                newNode.setDepth(currentNode.getDepth() + 1);
                newNode.setCost(currentNode.getCost() + 1);
                currentNode.addChild(newNode);

                fringe.push(newNode);
                //do we need this ?
                //  visited.add(newState.getCurrentPuzzle());
            }
        }
        return false;
    }

    public Node getResult() {
        return path;
    }

    public int getMaxDepth() {
        return maxDepth;
    }
}
