import java.util.*;

class Solution {

    public int[] gardenNoAdj(int n, int[][] paths) {

        // Adjacency list
        ArrayList<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Build graph
        for (int[] path : paths) {

            int u = path[0] - 1;
            int v = path[1] - 1;

            graph[u].add(v);
            graph[v].add(u);
        }

        // color[i] = flower type of garden i
        int[] color = new int[n];

        // Process every garden
        for (int i = 0; i < n; i++) {

            boolean[] used = new boolean[5];

            // Check colors used by neighbors
            for (int neighbor : graph[i]) {

                if (color[neighbor] != 0) {
                    used[color[neighbor]] = true;
                }
            }

            // Choose first available flower
            for (int flower = 1; flower <= 4; flower++) {

                if (!used[flower]) {
                    color[i] = flower;
                    break;
                }
            }
        }

        return color;
    }
}


// Interview Explanation — English

// You can say:

// "I model each garden as a vertex and each path as an undirected edge. This is a graph coloring problem with four available colors. For every garden, I check the colors already assigned to its neighboring gardens and choose any available color from 1 to 4. Since every garden has at most three neighbors and we have four colors, at least one color will always be available. Therefore, a simple greedy approach is sufficient."

// Complexity

// Building graph:

// O(E)

// Coloring:

// Each edge is checked at most twice:

// O(E)

// Overall:

// Time: O(V + E)
// Space: O(V + E)

// where:

// V = n
// E = paths.length
// 9. Interview Explanation — Hindi

// Interview me Hindi/Hinglish me:

// "Main gardens ko graph ke vertices aur paths ko edges maanunga. Ye basically graph coloring problem hai. Mere paas 4 flower types hain. Har garden ke neighbors ke colors check karke main 1 se 4 me se koi unused color assign kar dunga. Kyunki question me guarantee hai ki har garden ke maximum 3 neighbors hain aur mere paas 4 colors hain, isliye minimum ek color hamesha available rahega. Isliye backtracking ki need nahi hai, simple greedy coloring sufficient hai."

// 10. Important Follow-up Questions
// Q1. Why don't you need DFS/BFS?

// Answer:

// Because we don't need to traverse the graph to determine reachability or components.

// We only need to assign a valid color to each node.

// Also, degree ≤ 3 and colors = 4 guarantee a valid color.

// Q2. Why exactly 4 colors?

// Because the problem gives us 4 flower types.

// More importantly:

// maximum degree = 3
// colors = 4

// So:

// degree + 1 = 4

// At least one color is always free.

// Q3. What if there were only 3 flowers?

// Then greedy coloring would not always be guaranteed.

// Example:

// 1
// / \
// 2---3

// This triangle requires 3 colors, which happens to work.

// But a graph with maximum degree 3 can require 4 colors, such as K4:

//     1
//   / | \
//  2--3--4

// Every pair is connected, so all 4 need different colors.

// Q4. What if each garden had at most 4 paths?

// Then 4 colors would not guarantee a solution.

// A node could have four neighbors using:

// 1,2,3,4

// Then no color remains.

// Q5. Can we use an int[][] graph directly?

// Yes, but adjacency lists are more convenient because the graph is sparse and every node has at most 3 neighbors.

// Q6. Can we solve it using BFS/DFS?

// Yes. You can traverse every connected component using BFS/DFS and assign colors while visiting neighbors.

// But for this specific problem, greedy is simpler and more direct.

// Final takeaway

// Is problem ko yaad rakho as:

// Garden = Node
// Path = Edge
// Flower = Color

// Maximum degree = 3
// Available colors = 4

// => Graph Coloring
// => Greedy Coloring
// => Check neighbor colors
// => Pick any unused color

// Best approach: Greedy Coloring — O(V + E) time, O(V + E) space.
