class Solution {

    int[][] dp;
    int[] suffix;
    int n;

    public int stoneGameII(int[] piles) {

        n = piles.length;

        dp = new int[n][n + 1];
        suffix = new int[n + 1];

        // Suffix sum
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        return solve(0, 1, piles);
    }

    private int solve(int i, int M, int[] piles) {

        // No piles left
        if (i >= n) {
            return 0;
        }

        // Already calculated
        if (dp[i][M] != 0) {
            return dp[i][M];
        }

        int maxScore = 0;

        // Can take 1 to 2*M piles
        for (int X = 1; X <= 2 * M && i + X <= n; X++) {

            int newM = Math.max(M, X);

            // Current player's score
            int currentScore =
                    suffix[i] - solve(i + X, newM, piles);

            maxScore = Math.max(maxScore, currentScore);
        }

        dp[i][M] = maxScore;

        return maxScore;
    }
}