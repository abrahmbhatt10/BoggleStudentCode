import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Boggle {

    public static String[] findWords(char[][] board, String[] dictionary) {
        int boardLength = board.length;
        int boardLength2 = board[0].length;
        //ArrayList<String> goodWords = new ArrayList<String>();
        if(board == null || boardLength <= 0 || boardLength2 <= 0) {
            return null;
        }
        //Initialize the dictionary
        HashMap<String, Integer> mDict = new HashMap();
        HashMap<String, Integer> mWords = new HashMap();
        int dictLength = dictionary.length;;
        for(int i = 0; i < dictLength; i++)
        {
            mDict.put(dictionary[i], i);
        }
        String prefix = "";
        boolean[][] visited = new boolean [boardLength][boardLength2];

        for(int i = 0; i < boardLength; i++)
        {
            for(int j = 0; j < boardLength2; j++)
            {
                prefix = "";
                dfs(board, i, j,visited, prefix,mWords, mDict);
            }
        }
        /*
        // Below printing used for debugging purposes
        for(int i = 0; i < goodWords.size(); i++){
            System.out.println(goodWords.get(i));
        }
         */
        /*
        Code below taken from https://stackoverflow.com/questions/16246821/how-to-get-values-and-keys-from-hashmap

        for(String key : mWords.keySet()){
            goodWords.add(key);
        }
         */
        /*
        Below code converts the hashmap into a sorted array of strings, then return the array.
        Below code taken from: https://stackoverflow.com/questions/1090556/java-how-to-convert-hashmapstring-object-to-array
         */
        String[] sol = new String[mWords.keySet().size()];
        mWords.keySet().toArray(sol);
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
    public static void dfs(char[][] grid, int i, int j, boolean[][] visited, String prefix, HashMap<String, Integer> addWords, HashMap<String, Integer> mDict) {
        int gridLength = grid.length;
        int gridLength2 = grid[0].length;
        if (i < 0 || j < 0 || i >= gridLength
                || j >= gridLength2)  return;

        if (visited[i][j]) return;

        String currentPrefix = prefix + grid[i][j];
        // Below if statement checks if the word is in dictionary and isn't already in addWords.
        if(mDict.containsKey(currentPrefix) && addWords.containsKey(currentPrefix)) {
            //Valid prefix
            addWords.put(currentPrefix, addWords.size());
        }
        // Mark this square as visited
        visited[i][j] = true;
        dfs(grid, i - 1, j, visited, currentPrefix,addWords, mDict);
        dfs(grid, i + 1, j, visited, currentPrefix,addWords, mDict);
        dfs(grid, i, j - 1, visited, currentPrefix,addWords, mDict);
        dfs(grid, i, j + 1, visited, currentPrefix,addWords, mDict);

        // Mark this square as unvisited
        visited[i][j] = false;
    }
}
