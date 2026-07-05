class Solution {
    public String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");
        StringBuilder ans = new StringBuilder();
        for(int i = words.length-1;i>=0;i--){
            ans.append(words[i]);

            if(i != 0){
                ans.append(" ");
            }
        }
        return ans.toString();
        
    }
}



// "First, I remove the leading and trailing spaces using trim(). Then I split the string into words using split("\\\\s+"), 
// which handles multiple spaces. After that, I traverse the words array in reverse order and append each word to a StringBuilder, 
// adding a single space between them. This gives the reversed sentence in O(n) time with O(n) extra space."
