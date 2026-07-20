class Solution {
    public int twoCitySchedCost(int[][] costs) {

        Arrays.sort(costs, (a, b) -> (a[0] - a[1]) - (b[0] - b[1]));

        int n = costs.length / 2;
        int ans = 0;
        for(int i =0;i<n;i++){
            ans += costs[i][0];
        }

        for(int i =n;i<2*n;i++){
            ans += costs[i][1];
        }

        return ans;


        
    }
}


// "I calculate the difference (A - B) for every person and sort the array based on this difference.
// A large negative difference means sending that person to City A saves more money, while a large positive
// difference favors City B. After sorting, I send the first half of the people to City A and the remaining
// half to City B. This greedy strategy guarantees the minimum total cost."
