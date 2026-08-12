class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int l =0;
        int ans =0;
        for(int r=0;r<nums.length;r++){
            map.put(nums[r], map.getOrDefault(nums[r],0)+1); // Add current element

            while(map.get(nums[r])>k){     // If frequency becomes greater than k
                map.put(nums[l],map.get(nums[l])-1);
                l++;
            }
            ans = Math.max(ans,r-l+1);
        }
        return ans;
        
    }
}

// Interviewer ko simple words me:

// "I will use a sliding window with a HashMap to maintain the frequency of each element. I expand the right pointer and increase the frequency. 
// If the frequency of the newly added element becomes greater than k, I move the left pointer until the window becomes valid again. For every valid window, 
// I update the maximum length."



// class Solution {
//     public int maxSubarrayLength(int[] nums, int k) {

//         HashMap<Integer, Integer> map = new HashMap<>();

//         int left = 0;
//         int ans = 0;

//         for (int right = 0; right < nums.length; right++) {

//             // Current element ki frequency increase karo
//             if (map.containsKey(nums[right])) {
//                 map.put(nums[right], map.get(nums[right]) + 1);
//             } else {
//                 map.put(nums[right], 1);
//             }

//             // Agar frequency k se zyada ho gayi
//             while (map.get(nums[right]) > k) {

//                 // Left element ki frequency decrease karo
//                 map.put(nums[left], map.get(nums[left]) - 1);

//                 left++;
//             }

//             // Maximum window length
//             ans = Math.max(ans, right - left + 1);
//         }

//         return ans;
//     }
// }


////////////////////////////////////////////
// 1. Why Sliding Window?
// Interviewer: Brute force kyu nahi?
// Answer:
// Har possible subarray check karne par bahut zyada time lagega. Since hume longest contiguous subarray chahiye aur condition window ke andar frequency par depend karti hai, Sliding Window O(n) me solve karta hai.

// 2. Why HashMap?
// Interviewer: Frequency kaise maintain karoge?

// Answer:
// HashMap<Integer, Integer> map

// Map me:
// element → frequency
// store karenge.

// Example:
// [1, 2, 1, 3]

// 1 → 2
// 2 → 1
// 3 → 1
// 3. Why do you move left only when frequency > k?
// Answer:
// Jab current element ki frequency k se exceed ho jati hai, window invalid ho jati hai. left ko move karke us element ki frequency kam karte hain jab tak 
// window valid na ho jaye.

// 4. Why while, not if?

// Ye important interview follow-up hai.

// Answer:
// Because kabhi-kabhi ek element remove karne ke baad bhi window invalid ho sakti hai. Isliye while use karte hain.

// Concept:
// frequency > k
//       ↓
// remove left
//       ↓
// still > k ?
//       ↓
// remove again
// 5. Can we use HashSet instead of HashMap?
// Answer:
// No.
// HashSet sirf ye bata sakta hai ki element present hai ya nahi.
// Hume exact frequency chahiye:

// 1 → 3
// 2 → 2
// 3 → 1

// Isliye HashMap required hai.

// 6. Can we use Sorting?
// Answer:
// Sorting se original order change ho jayega.
// Question me subarray contiguous hona chahiye, isliye sorting valid approach nahi hai.

// 7. Why is the Time Complexity O(n), when there is a while loop inside for loop?
// Ye interviewer ka favorite question ho sakta hai.
// Answer:
// right pointer maximum n times move karta hai aur left pointer bhi maximum n times move karta hai.
// So total:
// right movements = O(n)
// left movements  = O(n)

// Total = O(2n) = O(n)

// Therefore:
// Time = O(n)
// Space = O(n)
// 8. What if k = 1?
// Then kisi bhi element ko window me maximum 1 time hi appear karna allowed hai.

// Example:

// nums = [1,2,1,3]
// k = 1
// Longest valid windows:
// [1,2]
// [2,1,3]
// Answer:
// 3
// 9. What if k >= nums.length?
// Then kisi bhi element ki frequency k se zyada nahi hogi.
// Therefore complete array valid hoga.
// answer = nums.length
    
// 10. Can we solve this using two pointers without HashMap?
// Answer:
// Not efficiently for arbitrary values.
// Two pointers alone window maintain kar sakte hain, but hume pata hona chahiye ki kis element ki frequency k se exceed hui.
// Uske liye frequency data structure chahiye, normally:
// HashMap<Integer, Integer>
