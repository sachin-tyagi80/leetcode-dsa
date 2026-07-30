class Solution {
    public int minimumPushes(String word) {
        int push = 0;
        int n= word.length();
        for(int i=0;i<n;i++){
            push += (i/8)+1;
        }
        return push;
    }
}