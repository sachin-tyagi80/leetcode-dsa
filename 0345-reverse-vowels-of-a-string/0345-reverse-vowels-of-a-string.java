class Solution {
    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        int left = 0, right = s.length() - 1;
        String vowels = "aeiouAEIOU";

        while (left < right) {
            while (left < right && vowels.indexOf(arr[left]) == -1) {
                left++;
            }
            while (left < right && vowels.indexOf(arr[right]) == -1) {
                right--;
            }
            if (left < right) {
                char temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }
        return new String(arr);
    }
}




// "I use the Two Pointer approach. One pointer starts from the left and the other from the right. 
// Both pointers skip non-vowel characters using indexOf(). When both point to vowels, I swap them and continue. 
// Finally, I convert the character array back to a string. The solution runs in O(n) time and uses O(n) extra space."
