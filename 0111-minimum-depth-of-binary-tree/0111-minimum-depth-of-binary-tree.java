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
import java.util.*;

class Solution {

    public int minDepth(TreeNode root) {

        // If tree is empty
        if (root == null) {
            return 0;
        }

        // Queue for BFS
        Queue<TreeNode> queue = new LinkedList<>();

        // Add root to queue
        queue.offer(root);

        // Root is at depth 1
        int depth = 1;

        // Continue until queue becomes empty
        while (!queue.isEmpty()) {

            // Number of nodes at current level
            int size = queue.size();

            // Process all nodes of current level
            for (int i = 0; i < size; i++) {

                // Remove front node
                TreeNode node = queue.poll();

                // If current node is a leaf,
                // this is the nearest leaf
                if (node.left == null && node.right == null) {
                    return depth;
                }

                // Add left child if it exists
                if (node.left != null) {
                    queue.offer(node.left);
                }

                // Add right child if it exists
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            // Move to next level
            depth++;
        }

        return depth;
    }
}