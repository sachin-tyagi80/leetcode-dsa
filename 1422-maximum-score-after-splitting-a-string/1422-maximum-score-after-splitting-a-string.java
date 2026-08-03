class Solution {
    public int maxScore(String s) {

        int ones = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '1') {
                ones++;
            }
        }

        int zeros = 0;
        int ans = 0;

        for (int i = 0; i < s.length() - 1; i++) {

            if (s.charAt(i) == '0') {
                zeros++;
            } else {
                ones--;
            }

            ans = Math.max(ans, zeros + ones);
        }

        return ans;
    }
}