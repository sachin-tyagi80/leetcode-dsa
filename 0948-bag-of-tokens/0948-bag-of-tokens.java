class Solution {
    public int bagOfTokensScore(int[] tokens, int power) {
         // Sort tokens in increasing order
        Arrays.sort(tokens);

        int left = 0;
        int right = tokens.length - 1;

        int score = 0;
        int maxScore = 0;

        while (left <= right) {

            // Play smallest token face-up
            if (power >= tokens[left]) {

                power -= tokens[left];

                score++;

                maxScore = Math.max(maxScore, score);

                left++;
            }

            // Play largest token face-down
            else if (score > 0) {

                power += tokens[right];

                score--;

                right--;
            }

            // No move possible
            else {

                break;
            }
        }

        return maxScore;
        
    }
}




// "I solved this problem using a Greedy approach with sorting and two pointers. After sorting the tokens, I always play the smallest token face-up whenever
// I have enough power because it gives one score at the minimum power cost. If I don't have enough power but I already have at least one score,
// I play the largest remaining token face-down to regain the maximum possible power. This greedy strategy maximizes the score while efficiently managing power."




////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// ⭐ Interview Follow-up
// Q1. Why do we always play the smallest token face-up?
// English: Because it costs the least power while giving exactly 1 score.
// Hindi: Kyunki har face-up move se sirf 1 score milta hai, isliye sabse kam power wala token choose karna best hai.

// Q2. Why do we always play the largest token face-down?
// English: Because losing one score is fixed, so we should regain the maximum possible power by using the largest token.
// Hindi: Kyunki face-down me 1 score hi lose hota hai, isliye badle me sabse zyada power lene ke liye sabse bada token use karna greedy choice hai.
