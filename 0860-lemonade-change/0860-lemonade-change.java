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