class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people); // sort weights
        int l=0;
        int r= people.length-1;
        int b = 0;
        while(l<=r){
            if(people[l] + people[r] <= limit){  // if lightest and heaviest can share a boat
                l++;
            }
            r--; // Heaviest person always goes
            b++;  // one boat is uesd
        }
        return b;
    }
}