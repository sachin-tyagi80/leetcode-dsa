class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {

        // Step 1: Sort the array
        Arrays.sort(arr);

        int minDiff = Integer.MAX_VALUE;

        // Step 2: Find the minimum difference
        for (int i = 1; i < arr.length; i++) {
            minDiff = Math.min(minDiff, arr[i] - arr[i - 1]);
        }

        List<List<Integer>> ans = new ArrayList<>();

        // Step 3: Collect all pairs having minimum difference
        for (int i = 1; i < arr.length; i++) {

            if (arr[i] - arr[i - 1] == minDiff) {

                ans.add(Arrays.asList(arr[i - 1], arr[i]));
            }
        }

        return ans;
    }
}