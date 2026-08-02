class Solution {
    public int minDeletions(String s) {
        int[] freq = new int[26];
        for(char ch : s.toCharArray()){
            freq[ch-'a']++;
        }
        HashSet<Integer> set = new HashSet<>();
        int del = 0;
        for(int f : freq){
            while(f>0 && set.contains(f)){
                f--;
                del++;
            }
            if(f>0){
                set.add(f);
            }
        }
        return del;
        
    }
}