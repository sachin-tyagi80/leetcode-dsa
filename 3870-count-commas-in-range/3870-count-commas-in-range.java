class Solution {
    public int countCommas(int n) {
        int count = 0;
        for(int i=1;i<=n;i++){
            if(i>=1000){
                count++;
            }
        }

        return count;
    }
}


// class Solution {

//     public int countCommas(int n) {

//         // 999 tak kisi number me comma nahi hota
//         if (n < 1000) {
//             return 0;
//         }

//         // 1000 se n tak har number me 1 comma hai
//         return n - 999;
//     }
// }




// Interviewer ko kaise explain karna hai 🎤

// Aap interview mein directly ye bol sakte ho:

// "First, I observe that commas start appearing from 1000 because numbers below 1000 have fewer than four digits. Since the constraint is n <= 10^5, every number from 1000 to n contains exactly one comma. Therefore, if n is less than 1000, the answer is 0. Otherwise, the number of such numbers is n - 1000 + 1, which simplifies to n - 999. So the solution takes O(1) time and O(1) space."

// Simple Hinglish version:

// "Sir, maine observe kiya ki 1000 se pehle kisi number mein comma nahi hota. 1000 se n tak har number mein exactly one comma hoga because n maximum 10^5 hai. Isliye agar n < 1000 hai to answer 0 hoga, otherwise numbers from 1000 to n ka count n - 1000 + 1, yani n - 999 hoga."

// 7. Follow-up Questions Interviewer pooch sakta hai
// Q1. Agar n = 999 ho?

// Answer:

// 0

// Because 999 mein comma nahi hai.

// Q2. Agar n = 1000 ho?
// 1

// Because only 1,000 contains a comma.

// Q3. Why n - 999 and not n - 1000?

// Because range 1000 to n inclusive hai.

// Formula:

// n - 1000 + 1
// = n - 999
// Q4. Agar constraint n <= 10^9 ho jaye to kya hoga?

// Ab simple n - 999 nahi chalega, because million/billion numbers mein multiple commas ho sakte hain.

// For example:

// 1,000       → 1 comma
// 1,000,000   → 2 commas
// 1,000,000,000 → 3 commas

// Then hume different digit ranges count karne padenge.

// Q5. Agar n = 1,000,000 ho to answer?

// Ranges:

// 1000 → 999999

// Each has 1 comma:

// 999999 - 1000 + 1 = 999000

// And:

// 1,000,000

// has 2 commas.

// Therefore:

// 999000 + 2 = 999002
// Q6. Kya String conversion se solve kar sakte hain?

// Yes:

// String s = String.valueOf(i);

// and commas count kar sakte hain, but that's O(n × digits) and unnecessary.

// Interview mein mathematical solution prefer karo.
