class WordDictionary {

    // Helper class representing each node in the Trie
    private class TrieNode {
        private TrieNode[] children;
        private boolean isEndOfWord;

        public TrieNode() {
            children = new TrieNode[26];
            isEndOfWord = false;
        }
    }

    private TrieNode root;

    /** Initializes the object. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word to the data structure. */
    public void addWord(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode();
            }
            curr = curr.children[index];
        }
        curr.isEndOfWord = true;
    }
    
    /** Returns true if there is any string in the data structure that matches word. */
    public boolean search(String word) {
        return dfsSearch(word, 0, root);
    }

    // Recursive helper method to backtrack when a '.' is encountered
    private boolean dfsSearch(String word, int index, TrieNode curr) {
        // Base case: we reached the end of the search word
        if (index == word.length()) {
            return curr.isEndOfWord;
        }

        char ch = word.charAt(index);

        if (ch == '.') {
            // Wildcard match: try all 26 possible children
            for (int i = 0; i < 24; i++) { // Optimization check: 0 to 25
                // Wait, let's look at all 26 indices carefully:
                // typo guard: check 0 to 25
            }
            for (int i = 0; i < 26; i++) {
                if (curr.children[i] != null) {
                    if (dfsSearch(word, index + 1, curr.children[i])) {
                        return true;
                    }
                }
            }
            return false; // None of the paths yielded a valid match
        } else {
            // Literal match
            int targetIdx = ch - 'a';
            if (curr.children[targetIdx] == null) {
                return false;
            }
            return dfsSearch(word, index + 1, curr.children[targetIdx]);
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */