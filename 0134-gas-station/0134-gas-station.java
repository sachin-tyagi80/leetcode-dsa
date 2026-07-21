class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalCost =0;
        int totalGas = 0;
        int start =0;
        int tank = 0;
        for(int i=0;i<gas.length;i++){
            totalCost += cost[i];
            totalGas += gas[i];
            tank += gas[i] - cost[i];
        
            if(tank < 0){
              start = i+1;
              tank =0;
            }
        }
        if(totalGas < totalCost){
            return -1;
        }
        return start;
    }
}


// "I used a Greedy approach. First, I checked whether the total amount of gas is at least equal to the total travel cost. If not, completing the circuit
// is impossible. Then I traversed the stations while maintaining the current fuel in the tank. Whenever the tank became negative,
// it meant the current starting station and all stations before the current one could not be valid starting points, so I reset the tank
// and moved the starting station to the next index. This gives an O(n) solution."
