package AStar;

import Utils.PuzzleChecker;

public class Main {
    public static void main(String[] args){
        String puzzle = "153647028";
        PuzzleChecker puzzleChecker = new PuzzleChecker();
        if(puzzleChecker.isSolvable((puzzle))) {
            System.out.println("Solvable");
            AStarSolver solver = new AStarSolver(puzzle);
            solver.solve(HeuristicFunctions::heuristicEuclidean);
            //printPath(solver.getResult());
            int maxDepth = solver.getMaxDepth();
            int cost = solver.getResult().getCost();
            int solDepth = solver.getResult().getDepth();
            System.out.println("Solution Depth = " + solDepth);
            System.out.println("Maximum Depth = " + maxDepth);
            System.out.println("Cost = " + cost);
            System.out.println("expanded nodes = " + solver.getNodesExpanded());
        } else {
            System.out.println("Not Solvable");
        }
    }
}
