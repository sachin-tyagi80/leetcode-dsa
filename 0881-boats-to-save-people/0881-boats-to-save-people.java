class Solution {
    public int numRescueBoats(int[] people, int limit) { 
        Arrays.sort(people);   // sort weights
        int left =0;
        int right = people.length-1;
        int boat = 0;
        while(left<=right){
            if(people[left]+people[right] <= limit){ // if lightest and heaviest can share a boat
                left++;
            }
            right--;  // Heaviest person always goes

            boat++;  // one boat  is uesd
        }
        return boat;
        
    }
}