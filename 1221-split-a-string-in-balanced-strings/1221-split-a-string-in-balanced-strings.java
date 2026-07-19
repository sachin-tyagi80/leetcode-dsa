class Solution {
    public int balancedStringSplit(String s) {
        int balance = 0;
        int count = 0;
        for(char ch : s.toCharArray()){    // Traverse the string
            if(ch == 'R'){                 // Increase balance for R
                balance++;
            }
            else{                           // Decrease balance for L
                balance--;
            }
            if(balance == 0){              // Current substring is balanced
                count++;
            }
        }
        return count;
        
    }
}



// I use a Greedy approach with a balance counter. Whenever I see 'R', I increment the balance, and whenever I see 'L',
// I decrement it. As soon as the balance becomes zero, it means the current substring has an equal number of L's and R's,
// so I immediately split the string. This greedy choice maximizes the number of balanced substrings.
