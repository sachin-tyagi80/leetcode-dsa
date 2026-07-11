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




// Sabse pehle main string me total 1 count karta hoon, kyunki shuru me saare 1 right substring me hote hain. 
// Phir main string ko left se right traverse karta hoon (last character tak nahi, kyunki dono parts non-empty hone chahiye). 
// Agar current character 0 hai to leftZeros badha deta hoon. Agar 1 hai to rightOnes ko kam kar deta hoon, kyunki woh 1 ab left part me aa gaya hai.
// Har split par leftZeros + rightOnes calculate karke maximum score update karta hoon. Is solution ki time complexity O(n) aur space complexity O(1) hai."
