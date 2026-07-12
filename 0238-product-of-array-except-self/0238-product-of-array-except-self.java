class Solution {
    public int[] productExceptSelf(int[] nums) {

        int n = nums.length;
        int[] ans = new int[n];

        ans[0] = 1;

        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }

        int suffix = 1;

        for (int i = n - 1; i >= 0; i--) {
            ans[i] *= suffix;
            suffix *= nums[i];
        }

        return ans;
    }
}



// "I solve this problem using the Prefix and Suffix Product approach. In the first pass, 
// I store the product of all elements to the left of each index in the answer array. In the second pass, 
// maintain a running suffix product and multiply it with the stored prefix product. 
// This gives the product of all elements except the current one without using division. The solution runs in O(n) time and O(1) extra space, 
// excluding the output array."
