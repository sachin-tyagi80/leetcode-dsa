class Solution {
    public String longestCommonPrefix(String[] strs) {

        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {

            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);

                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }

        return prefix;
    }
}


// 🎤 Interview Explanation

// Interview mein aise bol sakte ho:

// “I take the first string as the initial prefix. Then I compare it with every other string. If the current string does not start with the prefix, 
// I remove the last character from the prefix until it matches. If the prefix becomes empty, there is no common prefix, so I return an empty string.
// Finally, I return the remaining prefix.”

Hindi mein:
