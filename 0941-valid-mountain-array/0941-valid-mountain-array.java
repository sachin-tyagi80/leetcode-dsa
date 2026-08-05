class Solution {

    public boolean validMountainArray(int[] arr) {

        int n = arr.length;

        // Mountain must have at least 3 elements
        if (n < 3)
            return false;

        int i = 0;

        // Climb up the mountain
        while (i < n - 1 && arr[i] < arr[i + 1]) {
            i++;
        }

        // Peak cannot be first or last element
        if (i == 0 || i == n - 1)
            return false;

        // Walk down the mountain
        while (i < n - 1 && arr[i] > arr[i + 1]) {
            i++;
        }

        // If reached the end, it's a valid mountain
        return i == n - 1;
    }
}