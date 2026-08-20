// . Interview me Question ko kaise explain karein

// Interviewer ko pehle bolo:

// "We need to find the minimum depth from the root to the nearest leaf node. The depth is counted by the number of nodes, and a leaf is a node having no left and right children."

// Example:

//         3
//        / \
//       9   20
//          /  \
//         15   7

// Nearest leaf:

// 3 → 9

// So answer:

// 2
// 2. Approach 1 — DFS / Recursion
// Interview Explanation ⭐

// Aap bolo:

// "I can solve this using DFS recursion. For every node, I calculate the minimum depth of its left and right subtrees. However, I have to handle the case where one child is null separately, because a null child should not be considered as a leaf. If the current node is a leaf, I return 1. If only one child exists, I go through that child. If both children exist, I return 1 plus the minimum of the two subtree depths."

// Main logic
// Current Node
//      ↓
// Is it leaf?
//   /      \
// Yes       No
//  ↓         ↓
//  1       Children?
//           ↓
//     ┌─────┴─────┐
//     ↓           ↓
//  one child    both exist
//     ↓           ↓
//  go there    min(left,right)
// Code
class Solution {


    public int minDepth(TreeNode root) {


        // Empty tree
        if (root == null) {
            return 0;
        }


        // Leaf node
        if (root.left == null && root.right == null) {
            return 1;
        }


        // Only right child exists
        if (root.left == null) {
            return 1 + minDepth(root.right);
        }


        // Only left child exists
        if (root.right == null) {
            return 1 + minDepth(root.left);
        }


        // Both children exist
        return 1 + Math.min(
            minDepth(root.left),
            minDepth(root.right)
        );
    }
}
// Complexity
// Time  = O(N)
// Space = O(H)

// N = number of nodes
// H = height of tree

// 3. Approach 2 — BFS ⭐ Recommended

// Ab interviewer agar puche:

// "Can you solve it using BFS?"

// Aap bolo:

// "Yes. Since we need the nearest leaf, BFS is actually a natural choice because BFS visits the tree level by level. The first leaf node encountered must have the minimum depth. I use a queue and maintain the current depth. As soon as I find a leaf, I return the depth."

// Example
//         3             Level 1
//        / \
//       9   20           Level 2
//          /  \
//         15   7         Level 3

// BFS:

// Level 1 → 3
// Level 2 → 9, 20

// 9 leaf mil gaya.

// So immediately:

// answer = 2

// Hume remaining tree traverse karne ki zarurat nahi.

// 4. BFS Code
import java.util.*;


class Solution {


    public int minDepth(TreeNode root) {


        // Empty tree
        if (root == null) {
            return 0;
        }


        // Queue for BFS
        Queue<TreeNode> queue = new LinkedList<>();


        // Start with root
        queue.offer(root);


        // Root is at depth 1
        int depth = 1;


        while (!queue.isEmpty()) {


            // Number of nodes at current level
            int size = queue.size();


            // Process current level
            for (int i = 0; i < size; i++) {


                TreeNode node = queue.poll();


                // First leaf found = minimum depth
                if (node.left == null && node.right == null) {
                    return depth;
                }


                // Add left child
                if (node.left != null) {
                    queue.offer(node.left);
                }


                // Add right child
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
// Complexity
// Time  = O(N)
// Space = O(W)


// 6. Important Follow-up Questions
// Q1. Why is BFS better for this problem?

// Answer:

// "Because BFS explores nodes level by level. The first leaf we encounter is guaranteed to be the closest leaf to the root."

// Q2. Can we solve it using DFS?

// Answer:

// "Yes. We recursively calculate the minimum depth of the subtrees, but we need special handling when one child is null."

// Q3. Why can't we simply write this?
// return 1 + Math.min(
//     minDepth(root.left),
//     minDepth(root.right)
// );

// Answer:

// Because null child can return 0.

// Example:

// 1
//  \
//   2

// If we blindly use min():

// min(0, 1) = 0

// which is wrong.

// The correct answer is:

// 2

// So we must handle one-child cases separately.
// Complete logic

// Isko ek table me yaad rakho:

// Situation	What to do
// root == null	return 0
// Both children null	return 1
// Left null	Go right
// Right null	Go left
// Both exist	1 + min(left, right)

// Q4. What is a leaf node?

// Answer:

// "A leaf is a node whose left and right children are both null."

// node.left == null && node.right == null
// Q5. What if root is null?

// Answer:

// if (root == null) {
//     return 0;
// }

// Because there are no nodes.

// Q6. Why don't we count edges instead of nodes?

// The problem specifically defines depth as the number of nodes in the path.

// For:

// 3 → 9

// Edges = 1

// Nodes = 2

// Answer is:

// 2
// Q7. What is the maximum recursion depth in DFS?

// In a skewed tree:

// 1
//  \
//   2
//    \
//     3
//      \
//       4

// Recursion depth can become N.

// Therefore DFS space can be:

// O(N)

// in the worst case.

// Q8. What is BFS space complexity?
// BFS uses a queue.
// If maximum number of nodes at one level is W:
// Space = O(W)
// For a very wide tree, this can be large.

// Q9. Can we use iterative DFS?
// Yes.
// We can use a Stack instead of recursion, while storing:
// (node, depth)
// But for this particular problem, BFS is cleaner.

// Q10. Which approach would you choose in an interview?
// Best answer:
// "I would choose BFS because the problem asks for the minimum depth to the nearest leaf. BFS naturally explores levels from the root outward, so the first leaf found gives the answer immediately. DFS is also valid and takes O(N) time."

// final->>>>>
// Agar interviewer kahe "Solve Minimum Depth of Binary Tree", to concise answer:

// "I would use BFS. I put the root into a queue and process the tree level by level. I maintain a depth variable starting from 1. For each node, if it has no left and right children, it is a leaf, and because BFS processes nodes level by level, this is the nearest leaf. So I immediately return the current depth. Otherwise, I add its non-null children to the queue. The time complexity is O(N), and the space complexity is O(W), where W is the maximum width of the tree."
