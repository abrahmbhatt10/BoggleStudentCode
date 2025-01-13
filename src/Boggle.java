import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


public class Boggle {

    public static String[] findWords(char[][] board, String[] dictionary) {

        if(board == null || board.length <= 0 || board[0].length <= 0) {
            return null;
        }
        //Initialize the dictionary
        TST mDict = new TST();
        // Using a hashset because values are useless, and it doesn't add duplicates.
        HashSet<String> mWords = new HashSet<String>();
        for(int i = 0; i < dictionary.length; i++)
        {
            mDict.put(dictionary[i], i);
        }
        String prefix = "";
        boolean[][] visited = new boolean [board.length][board[0].length];
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[0].length; j++)
            {
                prefix = "";
                dfs(board, i, j,visited, prefix,mWords, mDict);
            }
        }
        /*
        Below code was already written by Mr. Blick
         */
        String[] sol = new String[mWords.size()];
        mWords.toArray(sol);
        Arrays.sort(sol);
        return sol;
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
    public static void dfs(char[][] grid, int i, int j, boolean[][] visited, String prefix, HashSet<String> addWords, TST mDict) {
        if (i < 0 || j < 0 || i >= grid.length
                || j >= grid[0].length)  return;

        if (visited[i][j]) return;

        String currentPrefix = prefix + grid[i][j];
        int subMatch = 0;
        // Below if statement checks if the word is in dictionary and isn't already in addWords.
        if(mDict.get(currentPrefix) != -1) {
            // Valid prefix
            addWords.add(currentPrefix);
        } else {
            // Get the number of substring characters matched.
            subMatch = mDict.getSubMatch();
            // Check for substring match
            if(subMatch != currentPrefix.length()-1)
            {
                // There is no substring match
                // Adding more characters to this prefix will not give valid words.
                return;
            }
        }
        visited[i][j] = true;
        // Mark this square as visited
        if(i-1 >= 0 && j >= 0 && i-1 < grid.length && j <grid[0].length && (!visited[i-1][j])){
            dfs(grid, i - 1, j, visited, currentPrefix,addWords, mDict);
        }
        if(i+1 >= 0 && j >= 0 && i+1 < grid.length && j <grid[0].length &&(!visited[i+1][j])) {
            dfs(grid, i + 1, j, visited, currentPrefix,addWords, mDict);
        }
        if(i >= 0 && j-1 >= 0 && i < grid.length && j-1 <grid[0].length &&(!visited[i][j-1])) {
            dfs(grid, i, j - 1, visited, currentPrefix,addWords, mDict);
        }
        if(i >= 0 && j+1 >= 0 && i < grid.length && j+1 <grid[0].length &&(!visited[i][j+1])){
            dfs(grid, i, j + 1, visited, currentPrefix,addWords, mDict);
        }
        visited[i][j] = false;
    }
}

