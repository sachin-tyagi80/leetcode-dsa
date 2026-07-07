class Solution {
    public long sumAndMultiply(int n) {
        if(n == 0){
            return 0;
        }
        String str = String.valueOf(n);
        long x = 0;
        long sum = 0;
        for(char ch : str.toCharArray()){
            int dig = ch - '0';
            if(dig != 0){
                x = x*10+dig;
                sum += dig;
            }
        }
        return x*sum;
        
    }
}



// "I traverse each digit of the number once. For every non-zero digit, I append it to a new number x and add it to the digit sum. 
// Finally, I return x * sum. Since each digit is processed only once, the time complexity is O(d) and the space complexity is O(1)."
