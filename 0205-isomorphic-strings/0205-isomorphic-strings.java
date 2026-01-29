class Solution {
    public boolean isIsomorphic(String s, String t) {
        int[] indexS = new int[200];// stores intex of char in string s
        int[] indexT = new int[200];// stores index of char in string t

        // get the length of both strings

        int len = s.length();

        // if the lenghts of the two string are different , they cant be isomorphic

        if(len !=s.length() ) {
            return false;
        }

        // iterate through each character of the strings

        for(int i = 0; i<len;i++){
            // check if the index of the current character in strings
            // is different from the index of the corresponding char in string t

            if(indexS[s.charAt(i)] != indexT[t.charAt(i)]){
                return false; // if different , strings are not isomorphic
            }

            // update the indices of character in both strings

            indexS[s.charAt(i)] = i+1; // updating index at current
            indexT[t.charAt(i)] = i+1;
        }
        return true;
    }
}