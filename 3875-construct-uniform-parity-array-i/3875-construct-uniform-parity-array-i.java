class Solution {
    public boolean uniformArray(int[] nums1) {
        int odd = 0;
        int even = 0;
        for(int num : nums1){
            if(num%2 == 0){
                even++;
            }else{
                odd++;
            }
        }

        if(odd == 0 || even == 0){ // if all number already have same parity
            return true;
        }
        
        return true; // Mixed parity can also be converted // into a uniform parity array
    }
}




// . Interview Explanation
// English

// "The key observation is that for any two numbers, subtraction gives even when their parities are the same and odd when their parities are different. Since we can choose either the original number or subtract another element, for every element we can choose a value having the desired parity. Therefore, a uniform parity array can always be constructed."

// Hindi

// "Main observation ye hai ki same parity ke do numbers ka subtraction even hota hai aur different parity ka subtraction odd hota hai. Har element ke liye hum original value ya kisi doosre element se subtraction choose kar sakte hain. Isliye sab elements ko same parity me convert karna possible hai."

// 6. Follow-up Questions
// Q1. Why does n = 1 work?

// Ek hi element hai, to obviously saare elements ki parity same hai.

// Q2. Why is distinctness important?

// Because j != i hai. Hume kisi doosre element ko subtraction ke liye choose karna hai.

// Q3. What is the complexity?
// Time:  O(n)
// Space: O(1)
