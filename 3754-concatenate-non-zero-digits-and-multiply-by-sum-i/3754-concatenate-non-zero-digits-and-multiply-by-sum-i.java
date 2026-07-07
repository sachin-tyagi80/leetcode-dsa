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