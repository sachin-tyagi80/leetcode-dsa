class Solution {
    public boolean sumGame(String num) {

        int n = num.length();

        int leftSum = 0;
        int rightSum = 0;

        int leftQ = 0;
        int rightQ = 0;

        // Left half
        for (int i = 0; i < n / 2; i++) {
            if (num.charAt(i) == '?') {
                leftQ++;
            } else {
                leftSum += num.charAt(i) - '0';
            }
        }

        // Right half
        for (int i = n / 2; i < n; i++) {
            if (num.charAt(i) == '?') {
                rightQ++;
            } else {
                rightSum += num.charAt(i) - '0';
            }
        }

        int diff = rightSum - leftSum;
        int qDiff = leftQ - rightQ;

        // Odd number of ? difference
        // means Alice can force unequal sums
        if (qDiff % 2 != 0) {
            return true;
        }

        // Bob can force equality
        if (diff == (qDiff / 2) * 9) {
            return false;
        }

        // Otherwise Alice wins
        return true;
    }
}