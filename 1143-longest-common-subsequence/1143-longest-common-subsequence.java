class Solution {
    public int longestCommonSubsequence(String text1, String text2) {

        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {

                    dp[i][j] = 1 + dp[i - 1][j - 1];

                } else {

                    dp[i][j] = Math.max(
                        dp[i - 1][j],
                        dp[i][j - 1]
                    );
                }
            }
        }

        return dp[m][n];
    }
}




// 15. Interview mein kya bolna hai?

// Aap interviewer ko ye bol sakte ho:

// "This is a Dynamic Programming problem. I define dp[i][j] as the length of the LCS between the first i characters of text1 and the first j characters of text2. If the current characters are equal, I include that character and use 1 + dp[i-1][j-1]. If they are different, I skip one character from either string and take the maximum of dp[i-1][j] and dp[i][j-1]. Finally, dp[m][n] gives the answer."

// 16. Interview Follow-up Questions
// Q1. Why do we use i-1 and j-1?

// Because dp uses 1-based prefix lengths, while Java strings use 0-based indexing.

// Q2. Why +1 when characters match?

// Because current character common hai, so LCS length mein one character add ho gaya.

// dp[i][j] = 1 + dp[i-1][j-1]
// Q3. Why max() when characters don't match?

// Because we have two possibilities:

// skip text1 character
// → dp[i-1][j]

// skip text2 character
// → dp[i][j-1]

// Best one choose karna hai.

// Q4. What is the time complexity?
// O(m × n)

// because every DP cell is calculated once.

// Q5. Can we optimize space?

// Yes.

// 2D array:

// O(m × n)

// se two rows / one-dimensional DP use karke:

// O(n)

// kar sakte hain.

// Q6. Can we find the actual LCS string, not just its length?

// Yes.

// 2D DP table ko backtrack karke actual sequence reconstruct kar sakte hain.

// For:

// abcde
// ace

// backtracking se:

// ace

// mil jayega.

// ⭐ Sabse Important LCS Formula

// Yaad rakho:

// If text1[i-1] == text2[j-1]:

// dp[i][j] = 1 + dp[i-1][j-1]

// Else:

// dp[i][j] = max(dp[i-1][j], dp[i][j-1])

// LCS ka core bas ye 2 cases hain.
