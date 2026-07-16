class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);  // Sort greed factors
        Arrays.sort(s);  // Sort cookie sizes
        int child = 0;
        int cookie = 0;

        while(child<g.length && cookie<s.length){   // Match children with cookies
            if(s[cookie] >= g[child]){   // Cookie is large enough
                child++;
                cookie++;
            }
            else{      // Cookie is too small
                cookie++;
            }
        }
        return child;
        
    }
}




// "I use a Greedy approach with sorting. I sort both the greed factors and cookie sizes in ascending order. Then I use two pointers. 
// If the current cookie is large enough to satisfy the current child, I assign it and move both pointers. Otherwise, I discard the small cookie because 
// it cannot satisfy the current child or any child with even greater greed. This greedy strategy maximizes the number of satisfied children."
