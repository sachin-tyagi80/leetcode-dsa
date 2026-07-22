class Solution {

    public boolean lemonadeChange(int[] bills) {

        // Count of $5 and $10 bills
        int five = 0;
        int ten = 0;

        // Process each customer
        for (int bill : bills) {

            // Customer pays $5
            if (bill == 5) {

                five++;
            }

            // Customer pays $10 
            else if (bill == 10) {

                // Need one $5 as change
                if (five == 0) {
                    return false;
                }

                five--;
                ten++;
            }

            // Customer pays $20
            else {

                // Prefer giving one $10 and one $5
                if (ten > 0 && five > 0) {

                    ten--;
                    five--;
                }

                // Otherwise give three $5 bills // 5,5,5
                else if (five >= 3) {

                    five -= 3;
                }

                // Change not possible
                else {

                    return false;
                }
            }
        }

        return true;
    }
}

// "I solved this problem using a Greedy approach. I only keep track of the number of $5 and $10 bills because $20 bills are never used as change. 
// For a $5 bill, I simply store it. For a $10 bill, I must give one $5 as change. For a $20 bill, I always prefer giving one $10 and one $5 instead
// of three $5 bills, because saving $5 bills is more beneficial for future transactions. If at any point I cannot provide the required change,
// I return false."

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// ⭐ Interview Follow-up
// Q. Why do we prefer giving $10 + $5 instead of $5 + $5 + $5?
// Because $5 bills are more valuable for future transactions. Every $10 payment requires a $5 bill as change, so preserving $5 bills increases 
// the chances of serving future customers.

// Hindi:
// Kyunki future me $10 wale customer ko change dene ke liye $5 bill zaroor chahiye hota hai. Agar hum 3 five-dollar bills de denge, 
// to baad me change dena mushkil ho sakta hai. Isliye Greedy choice hamesha $10 + $5 dena hai.
