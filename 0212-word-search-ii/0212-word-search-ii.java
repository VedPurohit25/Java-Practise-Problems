import java.util.*;

public class Solution {
    
    // Trie Node structure optimized for word collection
    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null; // Stores the complete word at the leaf node
    }
    
    // Helper function to build the Trie
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode curr = root;
            for (char ch : w.toCharArray()) {
                int idx = ch - 'a';
                if (curr.children[idx] == null) {
                    curr.children[idx] = new TrieNode();
                }
                curr = curr.children[idx];
            }
            curr.word = w; // Store full word at the end node
        }
        return root;
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        
        int m = board.length;
        int n = board[0].length;
        
        // Traverse every cell on the board
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                dfs(board, r, c, root, res);
            }
        }
        
        return res;
    }
    
    private void dfs(char[][] board, int r, int c, TrieNode node, List<String> res) {
        // Out of bounds or already visited check
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] == '#') {
            return;
        }
        
        char ch = board[r][c];
        TrieNode nextNode = node.children[ch - 'a'];
        
        // Prune path if the character doesn't match any prefix in the Trie
        if (nextNode == null) {
            return;
        }
        
        // Word found! Add it to the results
        if (nextNode.word != null) {
            res.add(nextNode.word);
            nextNode.word = null; // Avoid duplicate entries
        }
        
        // Mark cell as visited
        board[r][c] = '#';
        
        // Explore all 4 neighboring directions
        dfs(board, r + 1, c, nextNode, res); // Down
        dfs(board, r - 1, c, nextNode, res); // Up
        dfs(board, r, c + 1, nextNode, res); // Right
        dfs(board, r, c - 1, nextNode, res); // Left
        
        // Backtrack: restore the cell's original character
        board[r][c] = ch;
    }
}