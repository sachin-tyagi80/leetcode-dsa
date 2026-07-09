class Solution {
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character,Character> map1 = new HashMap<>();
        HashMap<Character,Character> map2 = new HashMap<>();
        
        for(int i=0;i<s.length();i++){
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if(map1.containsKey(c1)){
                if(map1.get(c1) != c2){
                    return false;
                }
            }else{
                map1.put(c1,c2);
            }
            if(map2.containsKey(c2)){
                if(map2.get(c2) != c1){
                    return false;
                }
            }else{
                map2.put(c2,c1);
            }
        }

        return true;
        
    }
}



// "I use two HashMaps to maintain a one-to-one mapping between the characters of both strings. 
// One map stores the mapping from s to t, and the other stores the reverse mapping from t to s. 
// While traversing both strings, I verify that every existing mapping is consistent. 
// If any mismatch is found, I return false; otherwise, I return true. The solution runs in O(n) time."
