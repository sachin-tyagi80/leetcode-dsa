class Solution {

    public int bestTeamScore(int[] scores, int[] ages) {

        int n = scores.length;

        int[][] players = new int[n][2];

        for (int i = 0; i < n; i++) {
            players[i][0] = ages[i];
            players[i][1] = scores[i];
        }

        Arrays.sort(players, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        int[] dp = new int[n];
        int ans = 0;

        for (int i = 0; i < n; i++) {

            dp[i] = players[i][1];

            for (int j = 0; j < i; j++) {

                if (players[j][1] <= players[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + players[i][1]);
                }
            }

            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }
}


// "I first pair each player's age and score, then sort them by age and, if the ages are equal, by score. After sorting,
// I apply Dynamic Programming similar to the Longest Increasing Subsequence. dp[i] stores the maximum team score ending with player i. 
// For each player, I check all previous players. If the previous player's score is less than or equal to the current player's score,
// they can be on the same team without conflict, so I update the DP value. Finally, I return the maximum value in the DP array. 
// The solution runs in O(n²) time and O(n) space
