/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) { // o(n)
        if(root == null){
            return false;
        }
        if(root.left == null && root.right == null){
            return targetSum == root.val;
        }
        targetSum -= root.val;

        return hasPathSum(root.left, targetSum) || hasPathSum(root.right, targetSum);
        
    }
}

// Interview Explanation (English)

// "I solve this problem using DFS recursion. At each node, I subtract the current node's value from the target sum. When I reach a leaf node, 
// I check whether the remaining target equals the leaf's value. If yes, I return true. Otherwise, I recursively search both the left and right subtrees. 
// If either subtree returns true, then a valid root-to-leaf path exists."

// Interview Questions
// Q1. Why do we check only at the leaf node?

// Answer:

// Because the problem asks for a root-to-leaf path. Reaching the target sum before a leaf is not enough.

// Q2. Why use DFS instead of BFS?

// Answer:

// DFS naturally follows one complete root-to-leaf path at a time, making it simple to maintain the running sum through recursion.

// Q3. Why subtract instead of adding?

// Answer:

// Subtracting avoids carrying an extra current-sum variable. At the leaf, we only need to check whether the remaining target equals the leaf's value.


