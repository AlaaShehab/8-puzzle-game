package Utils;

import java.util.ArrayList;

public class Node {

    private State state;
    private ArrayList<Node> children;
    private Node parent;
    private int depth;
    private int cost;

    public Node (State state) {
        children = new ArrayList<>();
        this.state = state;
    }
    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public Node getChild(String puzzle) {
        for (Node child : children) {
            if ((child.getState()).getCurrentPuzzle().equals(puzzle)) {
                return child;
            }
        }
        return null;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    @Override
    public boolean equals(Object o) {
        Node element = (Node)o;
        return this.getState().equals(((Node) o).getState());

    }
}