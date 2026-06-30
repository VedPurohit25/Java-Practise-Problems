import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0;
        int n = words.length;

        while (i < n) {
            int j = i + 1;
            int lineLength = words[i].length();

            // Greedy Step: Find out how many words can fit in the current line
            while (j < n && lineLength + words[j].length() + (j - i) <= maxWidth) {
                lineLength += words[j].length();
                j++;
            }

            StringBuilder sb = new StringBuilder();
            int wordCount = j - i;
            int gaps = wordCount - 1;

            // Scenario A & B: If it's the last line or a single-word line, left-justify it
            if (j == n || wordCount == 1) {
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) sb.append(" ");
                }
                // Pad the remaining empty spaces entirely to the right side
                while (sb.length() < maxWidth) {
                    sb.append(" ");
                }
            } else {
                // Scenario C: Standard fully justified line layout calculation
                int totalSpaces = maxWidth - lineLength;
                int baseSpaces = totalSpaces / gaps;
                int extraSpaces = totalSpaces % gaps;

                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) {
                        // Inject the base spaces required for every inner gap
                        int spacesToApply = baseSpaces + (k - i < extraSpaces ? 1 : 0);
                        for (int s = 0; s < spacesToApply; s++) {
                            sb.append(" ");
                        }
                    }
                }
            }

            result.add(sb.toString());
            i = j; // Advance pointer to begin formatting the next line layout
        }

        return result;
    }
}