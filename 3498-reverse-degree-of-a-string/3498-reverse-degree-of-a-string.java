class Solution {
    public int reverseDegree(String s) {
        int ans = 0;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);

            int reversePosition = 26 - (c - 'a');
            int stringPostion = i+1;

            ans += reversePosition * stringPostion;
        }
        return ans;
        
    }
}