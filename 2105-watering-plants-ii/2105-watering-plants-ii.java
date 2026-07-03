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




// "I use two pointers. Alice starts from the left, and Bob starts from the right. I keep track of the remaining water in both watering cans.
// If either doesn't have enough water for the current plant, they refill before watering it. 
// When both meet at the same plant, the one with more remaining water waters it; if both have the same water, 
// Alice waters it. Since each plant is processed once, the time complexity is O(n) and the space complexity is O(1)."
