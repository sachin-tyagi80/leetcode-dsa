class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> res = new ArrayList<>();
        Arrays.sort(nums);
        int curr = nums[0];
        int n = nums.length;
        for(int i=0;i<n;curr++,i++){
            if(curr<nums[i]){
                res.add(curr);
                i--;
            }
        }
        return res;
        
    }
}

// Interview Follow-up Questions
// Q1. Why sort the array?
// Answer:
// Sorting puts all numbers in increasing order, making it easy to detect gaps between consecutive numbers.

// Q2. Why initialize currEle = nums[0]?
// Answer:
// The problem states that the smallest element of the original range is present, so we start checking from the smallest number.

// Q3. Why use i--?
// Answer:
// After finding a missing number, the current array element has not been matched yet. i-- keeps the pointer on the same element while currEle moves to the next expected value.

// Q4. What happens if we remove i--?
// Answer:
// We skip comparing the current array element with the next expected number, producing incorrect results.

// Example:
// [1,2,4,5]
// Without i--, 4 gets skipped after detecting missing 3.

// Q5. Why don't we use a while loop instead of i--?
// Answer:
// We can. A while loop is another clean approach that repeatedly adds missing numbers until currEle == nums[i]. This implementation uses i-- to achieve the same behavior inside a for loop.

// Q6. What is the time complexity?
// Sorting: O(n log n)
// Traversing: O(n + missingCount)

// Overall:
// O(n log n)

// Q7. What is the space complexity?
// Ignoring the output list,
// O(1)
// because sorting is done in place (for the algorithmic analysis typically expected in interviews).

// Q8. Can we solve it without sorting?
// Yes.
// Using a HashSet → O(n) time, O(n) space.
// Using a boolean array (since nums[i] ≤ 100) → O(n) time, O(1) extra space.
// The boolean-array approach is the most efficient for this problem because of the small value range.

// 30-Second Interview Explanation
// "I sort the array first. Then I maintain an expected number currEle, starting from the smallest element. I compare it with the current array element. 
// If currEle is smaller, it means that number is missing, so I add it to the result and keep the array index at the same position using i--.
// Otherwise, I move to the next array element. This allows me to detect all missing numbers in the sorted range efficiently."

//////////////////////////////////////////////////////////////////////////////////
class Solution {

    public List<Integer> findMissingElements(int[] nums) {

        // Store missing numbers
        List<Integer> res = new ArrayList<>();

        // Boolean array to mark which numbers are present
        boolean[] contains = new boolean[101];

        // Find minimum and maximum element
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int ele : nums) {

            // Update minimum
            min = Math.min(min, ele);

            // Update maximum
            max = Math.max(max, ele);

            // Mark this number as present
            contains[ele] = true;
        }

        // Check every number from min to max
        for (int i = min; i <= max; i++) {

            // If number is not present, it is missing
            if (!contains[i]) {
                res.add(i);
            }
        }

        return res;
    }
}

// Interview Questions
// Q1. Why use a boolean array?
// Because the value range is very small (1–100).
// Checking whether a number exists becomes O(1).

// Q2. Why find min and max?
// The original continuous range starts from the smallest element and ends at the largest element.
// We don't need to check numbers outside this range.

// Q3. Why is the boolean array of size 101?
// Because:
// 1 <= nums[i] <= 100
// Index 100 must also be accessible, so we create an array of length 101 (indices 0 to 100).

// Complexity
// Time: O(n + (max - min))
// Space: O(101) ≈ O(1) because the array size is fixed.
// Interview Explanation (30 seconds)

// "I use a boolean array because the values are limited to the range 1–100. First, I find the minimum and maximum values while marking every 
// number as present in the boolean array. Then I iterate from the minimum to the maximum value. If a number is not marked as present, it is missing, 
// so I add it to the result. This avoids sorting and runs in linear time."


//////////////////////////////////////////////////////////////////
class Solution {

    public List<Integer> findMissingAndRepeatedValues(int[] nums) {

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        HashSet<Integer> set = new HashSet<>();

        // Find minimum, maximum and store all numbers
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
            set.add(num);
        }

        List<Integer> ans = new ArrayList<>();

        // Check every number in the range
        for (int i = min; i <= max; i++) {

            if (!set.contains(i)) {
                ans.add(i);
            }
        }

        return ans;
    }
}

// Interview Questions
// Q1. Why use HashSet?
// Because searching in a HashSet takes O(1) average time.
// Without a HashSet, we would need to scan the array for every number, making the solution slower.

// Q2. Why find the minimum and maximum first?
// The problem says the original array contained every number between the smallest and largest values.
// So we only need to check numbers in that range.

// Q3. Can we solve it by sorting?
// Yes.
// After sorting,
// 1 2 4 5
// Whenever the difference between adjacent numbers is greater than 1,
// the missing numbers are between them.
// Time Complexity:
// O(n log n)
// HashSet approach is better:
// O(n)
// Interview Explanation (30 sec)
// "I first find the minimum and maximum values because the original range lies between them. Then I store all elements in a HashSet for O(1) lookups.
// Finally, I iterate from the minimum to the maximum value and check whether each number exists in the HashSet. If a number is missing, I add it to the answer list.
// This gives an O(n) time solution."

// Complexity
// Time: O(n + (max - min))
// (Since nums[i] ≤ 100, this is effectively O(n).)
// Space: O(n) for the HashSet.
