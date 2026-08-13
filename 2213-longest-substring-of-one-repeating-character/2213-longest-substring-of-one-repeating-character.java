class Solution {
    static class Node {
        char prefixChar, suffixChar;
        int prefixLen, suffixLen, maxLen;
    }

    private Node[] tree;
    private char[] chars;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int k = queryIndices.length;
        chars = s.toCharArray();
        
        tree = new Node[4 * n];
        for (int i = 0; i < 4 * n; i++) {
            tree[i] = new Node();
        }

        // Build segment tree in O(n)
        build(1, 0, n - 1);

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char ch = queryCharacters.charAt(i);
            
            chars[idx] = ch;
            update(1, 0, n - 1, idx, ch);
            
            // Root maxLen gives the answer for the entire string s
            result[i] = tree[1].maxLen;
        }

        return result;
    }

    private void merge(Node parent, Node left, Node right, int leftLen, int rightLen) {
        parent.prefixChar = left.prefixChar;
        parent.suffixChar = right.suffixChar;
        
        // Calculate prefix length
        parent.prefixLen = left.prefixLen;
        if (left.prefixLen == leftLen && left.prefixChar == right.prefixChar) {
            parent.prefixLen += right.prefixLen;
        }

        // Calculate suffix length
        parent.suffixLen = right.suffixLen;
        if (right.suffixLen == rightLen && right.suffixChar == left.suffixChar) {
            parent.suffixLen += left.suffixLen;
        }

        // Calculate maximum contiguous substring length
        parent.maxLen = Math.max(left.maxLen, right.maxLen);
        if (left.suffixChar == right.prefixChar) {
            parent.maxLen = Math.max(parent.maxLen, left.suffixLen + right.prefixLen);
        }
    }

    private void build(int node, int l, int r) {
        if (l == r) {
            tree[node].prefixChar = chars[l];
            tree[node].suffixChar = chars[l];
            tree[node].prefixLen = 1;
            tree[node].suffixLen = 1;
            tree[node].maxLen = 1;
            return;
        }
        int mid = l + (r - l) / 2;
        build(2 * node, l, mid);
        build(2 * node + 1, mid + 1, r);
        merge(tree[node], tree[2 * node], tree[2 * node + 1], mid - l + 1, r - mid);
    }

    private void update(int node, int l, int r, int idx, char ch) {
        if (l == r) {
            tree[node].prefixChar = ch;
            tree[node].suffixChar = ch;
            tree[node].prefixLen = 1;
            tree[node].suffixLen = 1;
            tree[node].maxLen = 1;
            return;
        }
        int mid = l + (r - l) / 2;
        if (idx <= mid) {
            update(2 * node, l, mid, idx, ch);
        } else {
            update(2 * node + 1, mid + 1, r, idx, ch);
        }
        merge(tree[node], tree[2 * node], tree[2 * node + 1], mid - l + 1, r - mid);
    }
}