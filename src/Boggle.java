import java.util.ArrayList;
import java.util.Arrays;

public class Boggle {

    public static String[] findWords(char[][] board, String[] dictionary) {

        ArrayList<String> goodWords = new ArrayList<String>();
        // TODO: Complete the function findWords(). Add all words that are found both on the board
        //  and in the dictionary.

        // Convert the list into a sorted array of strings, then return the array.
        String[] sol = new String[goodWords.size()];
        goodWords.toArray(sol);
        Arrays.sort(sol);
        return sol;
    }

    /*
        Below code taken from Mr. Blick's slides:
     */
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    /*
        Below code taken from Mr. Blick's slides:
        Below pseudocode taken from Mr. Blick's slides:
        dfs(row, col, word):
	        if we have been here before, return
	        if this word is not a valid prefix, return
            mark this square as visited
            recurse up, down, left, right with updated word
            mark this square as not visited

     */
    public void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length
                || j >= grid[0].length)  return;

        if (grid[i][j] == '0') return;

        // Mark this square as visited
        grid[i][j] = '0';

        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }



}
