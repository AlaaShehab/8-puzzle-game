package BFS;

import Utils.Node;

public class Main {
    public static void main(String[] args) {
        BFSSolver bfsSolver = new BFSSolver("153047628");
        System.out.println(bfsSolver.solve());
        Node result = bfsSolver.getResult();
        if (result == null) {
            return;
        }
        System.out.println(result.getCost());
        System.out.println(result.getDepth());
        System.out.println(bfsSolver.getNodesExpanded());
        System.out.println(bfsSolver.getRunningTime());
        while (result != null) {
            System.out.println(result.getState().getCurrentPuzzle());
            result = result.getParent();
        }
    }
}
