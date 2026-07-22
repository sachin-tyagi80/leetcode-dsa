class Solution {
    public int maximum69Number (int num) {
        char[] arr = String.valueOf(num).toCharArray(); // Convert integer to Character Array
        for(int i=0;i<arr.length;i++){ // traverse from left to right
            if(arr[i] == '6'){  
                arr[i] = '9';
                break;  // only one change is allowed , so stop
            }
        }
        return Integer.parseInt(new String(arr)); // convert character array back to integer
        
    }
}



// "I solved this problem using a Greedy approach. Since the leftmost digit has the highest place value, 
// changing the first occurrence of 6 to 9 gives the maximum increase in the number. 
// convert the number into a character array, scan it from left to right, replace the first 6 with 9, and return the resulting number.
//     If no 6 exists,I simply return the original number.
