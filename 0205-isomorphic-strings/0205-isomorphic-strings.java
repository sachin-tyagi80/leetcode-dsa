class Solution {
    public boolean isIsomorphic(String s, String t) {

        int[] mapS = new int[256];
        int[] mapT = new int[256];

        for (int i = 0; i < s.length(); i++) {

            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if (mapS[c1] != mapT[c2]) {
                return false;
            }

            mapS[c1] = i + 1;
            mapT[c2] = i + 1;
        }

        return true;
    }
}


// "Since the input contains only ASCII characters, I use two arrays of size 256 instead of HashMaps. 
// Each array stores the last seen position of a character. While traversing both strings, 
// the last seen positions of the current characters must always match. If they don't, the strings are not isomorphic. 
// This solution runs in O(n) time with O(1) extra space and is faster than the HashMap approach due to direct array indexing."
