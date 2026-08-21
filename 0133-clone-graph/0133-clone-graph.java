/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/
import java.util.*;

class Solution {

    HashMap<Node, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {

        if (node == null) {
            return null;
        }

        // Already cloned
        if (map.containsKey(node)) {
            return map.get(node);
        }

        // Create clone
        Node clone = new Node(node.val);

        // Store before visiting neighbors
        map.put(node, clone);

        // Clone all neighbors
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor));
        }

        return clone;
    }
}



// Interview mein kaise explain karna hai?

// Aap interviewer ko approximately ye bol sakte ho:

// "I need to create a deep copy of an undirected graph. Since the graph can contain cycles, I will maintain a HashMap that maps each original node to its cloned node. During DFS, if a node is already present in the map, I return its existing clone. Otherwise, I create a new node, store it in the map before processing its neighbors, and recursively clone all neighbors. This prevents infinite recursion and ensures every original node has exactly one clone."

// One very important sentence:

// "I store the clone in the HashMap before visiting its neighbors to handle cycles."

// Ye point interviewer ko definitely explain karna.


// 13. Interview Follow-up Questions
// Q1. Why do we need HashMap?

// Answer:
// Because the graph can contain cycles and the same node can be reached through multiple paths. HashMap prevents duplicate cloning and infinite recursion.

// Q2. Why not use only visited[]?

// Answer:
// A normal visited array only tells us whether a node was visited. We need to know which clone corresponds to that original node, so we need:

// Original Node → Clone Node

// Hence HashMap is appropriate.

// Q3. Why store clone before processing neighbors?

// Answer:
// To handle cycles. If a neighbor points back to the current node, we can immediately return its already-created clone.

// Q4. Can we solve it using BFS?

// Answer:
// Yes. Use a queue and the same HashMap. Time remains O(V + E).

// Q5. What is a deep copy?

// Answer:
// A completely independent graph where every node is a newly created object, while preserving the same values and connections.

// ⭐ Remember this pattern

// Clone Graph =

// Graph Traversal
//       +
// HashMap
//       +
// Create clone
//       +
// Store clone BEFORE neighbors

////////////////////////////////////////////
// import java.util.*;

// class Solution {

//     public Node cloneGraph(Node node) {

//         if (node == null) {
//             return null;
//         }

//         HashMap<Node, Node> map = new HashMap<>();
//         Queue<Node> queue = new LinkedList<>();

//         // Create clone of starting node
//         Node clone = new Node(node.val);

//         map.put(node, clone);
//         queue.offer(node);

//         while (!queue.isEmpty()) {

//             Node current = queue.poll();

//             for (Node neighbor : current.neighbors) {

//                 // If neighbor is not cloned
//                 if (!map.containsKey(neighbor)) {

//                     Node newNode = new Node(neighbor.val);

//                     map.put(neighbor, newNode);

//                     queue.offer(neighbor);
//                 }

//                 // Connect cloned current to cloned neighbor
//                 map.get(current).neighbors.add(map.get(neighbor));
//             }
//         }

//         return clone;
//     }
// }


// Interview mein line-by-line explanation

// Interviewer pooche:

// "Explain your approach."

// Aap bolo:

// "I will use BFS to traverse the graph and a HashMap to maintain the mapping between every original node and its cloned node."

// Then:

// "First, I handle the null case. Then I create the clone of the starting node, store the mapping in the HashMap, and put the original node into the queue."

// Then main loop:

// "While the queue is not empty, I take one original node and process all its neighbors."

// Then:

// "If a neighbor has not been cloned yet, I create its clone, store it in the HashMap, and add the original neighbor to the queue."

// Then:

// "Whether the neighbor is new or already cloned, I connect the cloned current node with the cloned neighbor using the HashMap."

// Finally:

// "After BFS completes, the starting node's clone is the root of the complete cloned graph, so I return it."


// 10. Follow-up Questions
// Q1. What happens if node == null?
// if (node == null) {
//     return null;
// }

// Because empty graph ka clone bhi null hoga.

// Q2. Why HashMap instead of HashSet?

// HashSet sirf bata sakta hai:

// visited?

// But hume clone bhi chahiye.

// We need:

// Original Node → Clone Node

// Therefore:

// HashMap<Node, Node>
// Q3. Can we use HashMap<Integer, Node> instead?

// Technically, given problem mein val unique hai, so possible hai.

// But better design:

// HashMap<Node, Node>

// because we're mapping the actual original object to its cloned object.

// Q4. Why is the graph's val not enough to identify the node?

// In this problem val unique hai, but generally graph nodes may not have unique values.

// So object reference based mapping is more general:

// Node object → cloned Node object
// Q5. What is the difference between shallow copy and deep copy?

// Shallow copy:

// 1' → original 2

// Some references original graph ke nodes ko point karte hain.

// Deep copy:

// 1' → 2'

// All nodes are newly created.

// Q6. What happens with a cycle?

// Example:

// 1 → 2 → 3 → 1

// When we reach 1 again:

// map.containsKey(1)

// is true.

// So we don't create another 1'.

// We simply use:

// map.get(1)

// This prevents infinite traversal.

// Q7. What if there is only one node?

// Input:

// [[]]

// We create:

// 1'

// Its neighbors are empty.

// Return:

// 1'
// Q8. What if the graph has 100 nodes?

// No issue.

// Each node is cloned once and processed once.

// Time = O(V + E)
// Space = O(V)
// 11. Strong Interview Explanation — 30 Seconds

// Agar interviewer bole "Explain in short", ye answer yaad rakho:

// "I'll use BFS with a HashMap. The HashMap stores the mapping from every original node to its cloned node. I first clone the starting node, put the mapping into the HashMap, and push the original node into a queue. Then I process nodes using BFS. For each neighbor, if it hasn't been cloned, I create its clone, store it in the map, and add the original neighbor to the queue. Then I connect the current cloned node with the neighbor's cloned node. The HashMap also handles cycles because an already cloned node is reused instead of being cloned again. The time complexity is O(V + E) and space complexity is O(V)."

// ⭐ Interview keywords
// BFS
// +
// Queue
// +
// HashMap
// +
// Original → Clone mapping
// +
// Cycle handling
// +
// O(V + E)
