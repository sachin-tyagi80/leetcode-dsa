class Solution {
    public int reverseDegree(String s) {
        int ans = 0;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);

            int reversePosition = 26 - (c - 'a');
            int stringPostion = i+1;

            ans += reversePosition * stringPostion;
        }
        return ans;
        
    }
}


//////////////////////////////////////////////////////////////////////////
// 7. Ek important point: char - 'a'

// Interview mein ye concept poocha ja sakta hai.

// Java mein characters internally numeric values represent karte hain.

// For example:

// 'a' - 'a' = 0
// 'b' - 'a' = 1
// 'c' - 'a' = 2
// ...
// 'z' - 'a' = 25

// Therefore:

// 26 - (c - 'a')

// automatically reverse alphabet position de deta hai.

// 8. Alternative Approach — Direct Formula

// Hum reverse value ko directly:

// 'z' - c + 1

// se bhi calculate kar sakte hain.

// Because:

// z - a + 1 = 26
// z - b + 1 = 25
// z - z + 1 = 1

// Code:

// class Solution {
//     public int reverseDegree(String s) {
//         int ans = 0;

//         for (int i = 0; i < s.length(); i++) {
//             int reverseValue = 'z' - s.charAt(i) + 1;

//             ans += reverseValue * (i + 1);
//         }

//         return ans;
//     }
// }

// Ye bhi O(n) hai.

// Main recommend karunga:
// 26 - (c - 'a')

// because it clearly shows alphabet position logic.

// 9. Complexity

// String ki length n hai.

// Hum har character ko exactly once process kar rahe hain.

// Time Complexity:  O(n)
// Space Complexity: O(1)

// Constraints mein:

// n <= 1000

// so easily efficient hai.

// 10. Interview mein kya bolna hai?

// Aap interviewer ko ye bol sakte ho:

// "For every character, I calculate its reverse alphabet position using 26 - (c - 'a'). Since the string position is 1-indexed, I use i + 1. Then I multiply these two values and add the result to the answer. We process each character once, so the time complexity is O(n) and the space complexity is O(1)."

// Short version:

// "I simulate the definition directly: reverse alphabet value × 1-based string position, and sum all products."

// 11. Interview Follow-up Questions
// Q1. Why i + 1 instead of i?

// Because array/string indexing Java mein 0-based hoti hai, but problem position 1-based maang raha hai.

// i = 0 → position 1
// i = 1 → position 2
// Q2. Why is reverse value of a equal to 26?

// Normal alphabet mein:

// a = 1

// Reverse mein:

// a = 26

// Formula:

// 26 - ('a' - 'a') = 26
// Q3. Reverse value of z?
// 26 - ('z' - 'a')
// = 26 - 25
// = 1
// Q4. Can we use an array/map?

// Yes:

// int[] value = new int[26];

// But unnecessary hai. Formula se directly value mil rahi hai, so extra space ki need nahi.

// Q5. What if string contains uppercase letters?

// Problem constraints ke according:

// s contains only lowercase English letters

// So uppercase handle karne ki requirement nahi hai.

// Q6. What if the same character occurs multiple times?

// Har occurrence ki string position different hogi.

// For "aaa":

// a → 26 × 1
// a → 26 × 2
// a → 26 × 3

// So repeated character bhi separately calculate hoga.

// ⭐ Final Formula Yaad Rakho
// Reverse Alphabet Value = 26 - (c - 'a')

// String Position = i + 1

// Contribution = Reverse Value × (i + 1)

// Answer = Sum of all contributions

// Bas isi logic se 3498 Reverse Degree of a String solve ho jata hai.
