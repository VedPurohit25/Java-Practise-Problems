import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        
        Map<String, List<String>> map = new HashMap<>();
        
        for (String s : strs) {
            // Convert string to character array and sort it
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String signature = String.valueOf(chars);
            
            // Group original strings by their sorted signature
            if (!map.containsKey(signature)) {
                map.put(signature, new ArrayList<>());
            }
            map.get(signature).add(s);
        }
        
        return new ArrayList<>(map.values());
    }
}