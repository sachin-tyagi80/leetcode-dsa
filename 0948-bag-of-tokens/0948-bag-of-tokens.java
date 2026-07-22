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