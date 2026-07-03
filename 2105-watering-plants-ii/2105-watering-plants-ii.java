class Solution {
    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int l = 0;
        int r  = plants.length-1;
        int waterA = capacityA;
        int waterB = capacityB;
        int refill = 0;

        while(l<r){
            if(waterA < plants[l]){
                refill++;
                waterA = capacityA;
            }
            waterA -= plants[l];
            l++;

            if(waterB < plants[r]){
                refill++;
                waterB = capacityB;
            }
            waterB -= plants[r];
            r--;

        }
        if(l == r){
            if (Math.max(waterA, waterB) < plants[l]) {
                refill++;
            }
        }
        return refill;        
    }
}