class Solution {
    public boolean uniformArray(int[] nums1) {
        int odd = 0;
        int even = 0;
        for(int num : nums1){
            if(num%2 == 0){
                even++;
            }else{
                odd++;
            }
        }

        if(odd == 0 || even == 0){ // if all number already have same parity
            return true;
        }
        
        return true; // Mixed parity can also be converted // into a uniform parity array
    }
}