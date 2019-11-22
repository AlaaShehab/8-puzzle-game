package Utils;

import Utils.Node;

public class PuzzleChecker {

    // A utility function to count
// inversions in given array 'arr[]'
    private int getInvCount(int[] arr)
    {
        int inv_count = 0;
        for (int i = 0; i < arr.length -1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                // Value 0 is used for empty space
                if (arr[i] > 0 && arr[j] > 0 &&
                        arr[i] > arr[j]) {
                    inv_count++;
                }
            }
        }
        return inv_count;
    }

    // This function returns true
// if given 8 puzzle is solvable.
    public boolean isSolvable(String puzzle)
    {
        int[] puzzleFormatted = changeFormat(puzzle);
        if(puzzle==null) {
            return false;
        }

        // Count inversions in given 8 puzzle
        int invCount = getInvCount(puzzleFormatted);

        // return true if inversion count is even.
        return (invCount % 2 == 0);
    }

    private int[] changeFormat(String puzzle) {
        int[] ret_val = new int[9];
        if(puzzle.length() != 9)
            return null;
        for(int i=0; i< 9;i++)
            ret_val[i] = Character.getNumericValue(puzzle.charAt((i)));
        return ret_val;
    }
}
