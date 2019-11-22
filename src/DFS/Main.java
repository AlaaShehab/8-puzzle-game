package DFS;

import Utils.PuzzleChecker;

public class Main {
    /* Driver code */
    public static void main(String args[]) {
        String puzzle = "528417036";
        PuzzleChecker puzzleChecker = new PuzzleChecker();
        if(puzzleChecker.isSolvable(puzzle)) {
            System.out.println("Solvable");
            DFSSolver solver = new DFSSolver(puzzle);
            solver.solve();
            //printPath(solver.getPath());
            int maxDepth = solver.getMaxDepth();
            int cost = solver.getResult().getCost();
            int solDepth = solver.getResult().getDepth();
            System.out.println("Solution Depth = " + solDepth);
            System.out.println("Maximum Depth = " + maxDepth);
            System.out.println("Cost = " + cost);
        } else {
            System.out.println("Not Solvable");
        }
    }
}
