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
        int curr = 0;
        for(int i=0;i<=1000;i++){
            curr += diff[i];

            if(curr > capacity){
                return false;
            }
        }
        return true;
        
    }
}