class Trie {
    
    // Nested helper class representing each node in the Trie
    private class TrieNode {
        private TrieNode[] children;
        private boolean isEndOfWord;

        public TrieNode() {
            children = new TrieNode[26]; // 26 letters for 'a' through 'z'
            isEndOfWord = false;
        }
    }

    private TrieNode root;

    /** Initializes the trie object. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts the string word into the trie. */
    public void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a'; // Convert character to 0-25 index
            
            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode();
            }
            curr = curr.children[index];
        }
        curr.isEndOfWord = true; // Mark the end of the word
    }
    
    /** Returns true if the string word is in the trie and false otherwise. */
    public boolean search(String word) {
        TrieNode node = traverse(word);
        return node != null && node.isEndOfWord;
    }
    
    /** Returns true if there is a previously inserted string word that has the prefix prefix. */
    public boolean startsWith(String prefix) {
        return traverse(prefix) != null;
    }

    /** Helper method to traverse down the tree given a string */
    private TrieNode traverse(String str) {
        TrieNode curr = root;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int index = ch - 'a';
            
            if (curr.children[index] == null) {
                return null; // Path broken, string doesn't exist
            }
            curr = curr.children[index];
        }
        return curr;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */