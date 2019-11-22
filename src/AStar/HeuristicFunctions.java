package AStar;
import java.awt.*;
import java.util.Arrays;

public class HeuristicFunctions {

    private static final int DIM = 3;
    private static String goalPuzzle = "012345678";

    public static int heuristicManhattan(String puzzle) {
        int cost = 0;
        //point.x Current , point.y Goal
        Point[] positions = setCurrentGoalPositions(puzzle);
        for (int i = 1; i < puzzle.length(); i++) {
            cost += getManhattanDistant(positions[i]);
        }
        return cost;
    }

    public static int heuristicEuclidean(String puzzle) {
        int cost = 0;
        Point[] positions = setCurrentGoalPositions(puzzle);
        for (int i = 1; i < puzzle.length(); i++) {
            cost += getEuclideanDistant(positions[i]);
        }
        return cost;
    }

    private static int getManhattanDistant(Point position) {
        int ans = 0;
        ans += Math.abs(position.x / DIM - position.y / DIM);
        ans += Math.abs(position.x % DIM - position.y % DIM);
        return ans;
    }

    private static int getEuclideanDistant(Point position) {
        float ans = 0;
        ans += Math.pow((float)position.x / DIM - (float)position.y / DIM, 2);
        ans += Math.pow((float)position.x % DIM - (float)position.y % DIM, 2);
        return (int) Math.sqrt(ans);
    }

    private static Point[] setCurrentGoalPositions(String puzzle) {
        Point[] positions = new Point[9];
        for (int i = 0; i < puzzle.length(); i++) {
            char cCurrent = puzzle.charAt(i);
            int index1 = Character.getNumericValue(cCurrent);
            if(positions[index1] == null)
                positions[index1] = new Point();
            positions[index1].x = i;
            char cGoal = goalPuzzle.charAt(i);
            int index2 = Character.getNumericValue(cGoal);
            if(positions[index2] == null)
                positions[index2] = new Point();
            positions[index2].y = i;
        }
        return positions;
    }
}
