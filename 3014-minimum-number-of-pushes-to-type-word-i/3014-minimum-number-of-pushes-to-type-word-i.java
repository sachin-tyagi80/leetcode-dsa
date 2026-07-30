class Solution {
    public int minimumPushes(String word) {
        int push =0;
        int n = word.length();
        for(int i=0;i<n;i++){   // Traverse every character
            push += (i/8)+1;   // Every group of 8 letters requires one extra push
        }

        return push;
        
    }
}