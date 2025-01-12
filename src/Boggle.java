import java.util.ArrayList;
import java.util.Arrays;


public class Boggle {

    public static String[] findWords(char[][] board, String[] dictionary) {

        ArrayList<String> goodWords = new ArrayList<String>();
        if(board == null || board.length <= 0 || board[0].length <= 0) {
            return null;
        }
        //Initialize the dictionary
        SlimTrie mDict = new SlimTrie();
        SlimTrie mWords = new SlimTrie();
        for(int i = 0; i < dictionary.length; i++)
        {
            mDict.insert(dictionary[i]);
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
        goodWords = mWords.getWordsList();
        String[] sol = (String[]) goodWords.toArray();
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
    public static void dfs(char[][] grid, int i, int j, boolean[][] visited, String prefix, SlimTrie addWords, SlimTrie mDict) {
        if (i < 0 || j < 0 || i >= grid.length
                || j >= grid[0].length)  return;

        if (visited[i][j]) return;

        String currentPrefix = prefix + grid[i][j];
        // Below if statement checks if the word is in dictionary and isn't already in addWords.
        if(mDict.contains(currentPrefix) && !addWords.contains(currentPrefix)) {
            //Valid prefix
            addWords.add(currentPrefix);
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
