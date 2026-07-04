class Solution {
    public int wateringPlants(int[] plants, int capacity) {
        int water = capacity;
        int steps = 0;
        for(int i =0;i<plants.length;i++){
            if(water<plants[i]){
                steps += 2*i;
                water = capacity;

            }
            steps++;
            water -= plants[i];
        }
        return steps;
        
    }
}


// I simulate the watering process from left to right. I keep track of the remaining water and the total steps. 
// Before watering each plant, I check if the remaining water is enough. 
// If not, I add the steps required to return to the river and come back, refill the watering can, and continue. 
// The solution runs in O(n) time and uses O(1) extra space. inteviewer best explain code short and simple
