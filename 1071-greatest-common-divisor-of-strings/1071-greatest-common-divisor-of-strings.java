class Solution {
    public String gcdOfStrings(String str1, String str2) {
        if((str1 + str2).equals(str2 + str1)){
            int lengcd = gcd(str1.length(),str2.length());
            return str1.substring(0,lengcd);
        }else{
            return "";
        }
    }
    int gcd(int a , int b){
        while(a %b != 0){
            int rem = a%b;
            a = b;
            b  = rem;
        }
        return b;
    }
}