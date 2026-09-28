class Solution {
    public int maxDepth(String s) {
        int d =0;
        int maxDepth = 0;
        for(char c : s.toCharArray()){
            if(c == '('){
                d++;
                maxDepth = Math.max(maxDepth,d);
            }
            else{
                if(c == ')'){
                    d--;
                }
            }
        }
        return maxDepth;
    }
}