class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] diff = new int[1001];
        for(int[] trip : trips){
            int passengers = trip[0];
            int start = trip[1];
            int end = trip[2];


            diff[start] += passengers;
            diff[end] -= passengers;
        }

        int current = 0;
        for(int i=0;i<=1000;i++){
            current += diff[i];

            if(current>capacity){
                return false;
            }
        }
        return true; 
        
    }
}