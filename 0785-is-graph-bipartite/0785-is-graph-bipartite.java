class Solution {

    public boolean isBipartite(int[][] graph) {

        int n = graph.length;

        // -1 means no color
        int[] color = new int[n];

        Arrays.fill(color, -1);

        // Check every component
        for (int i = 0; i < n; i++) {

            if (color[i] == -1) {

                // Give starting node color 0
                color[i] = 0;

                Queue<Integer> q = new LinkedList<>();
                q.add(i);

                while (!q.isEmpty()) {

                    int curr = q.remove();

                    // Check all neighbors
                    for (int neighbor : graph[curr]) {

                        // Neighbor is not colored
                        if (color[neighbor] == -1) {

                            // Give opposite color
                            color[neighbor] = 1 - color[curr];

                            q.add(neighbor);
                        }

                        // Neighbor already colored
                        else if (color[neighbor] == color[curr]) {

                            // Same color connected by an edge
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}




// Interview explanation

// "I use BFS with two-coloring. Initially all nodes have color -1. For every uncolored component, I assign color 0 to the starting node and push it into the queue.
// While processing a node, every uncolored neighbor gets the opposite color. If an already colored neighbor has the same color as the current node, 
// then the graph is not bipartite, so I return false. Since the graph can be disconnected, I repeat BFS for every uncolored node."

////////////////////////////////////////////////////////////////////////////////////////////
// class Solution {

//     public boolean isBipartite(int[][] graph) {

//         int n = graph.length;

//         // -1 means no color
//         int[] color = new int[n];

//         for (int i = 0; i < n; i++) {
//             color[i] = -1;
//         }

//         // Graph can be disconnected
//         for (int i = 0; i < n; i++) {

//             if (color[i] == -1) {

//                 color[i] = 0;

//                 if (!dfs(graph, i, color)) {
//                     return false;
//                 }
//             }
//         }

//         return true;
//     }

//     private boolean dfs(int[][] graph, int curr, int[] color) {

//         for (int neighbor : graph[curr]) {

//             // Neighbor has no color
//             if (color[neighbor] == -1) {

//                 // Give opposite color
//                 color[neighbor] = 1 - color[curr];

//                 if (!dfs(graph, neighbor, color)) {
//                     return false;
//                 }
//             }

//             // Neighbor already has a color
//             else if (color[neighbor] == color[curr]) {

//                 // Same color connected by an edge
//                 return false;
//             }
//         }

//         return true;
//     }

// }

///////////////////////////////
// ⭐ Interview me short explanation

// English:
// "I use DFS with two-coloring. Initially every node has color -1. For every uncolored component, I assign color 0 to the starting node. For each neighbor, 
// I assign the opposite color using 1 - color[curr]. If a neighbor is already colored and has the same color as the current node, 
// then the graph is not bipartite. Since the graph can be disconnected, I run DFS from every uncolored node."

// 🧠 Agar interviewer bole: Why 1 - color[curr]?

// Aap bolo:

// “Because I am using only two colors: 0 and 1. If the current color is 0, 1 - 0 gives 1. If the current color is 1, 1 - 1 gives 0. So it always gives the opposite color.”

// ❓ Agar interviewer bole: Why BFS?

// “BFS lets me process the graph level by level. When I visit a vertex, I give its neighbours the opposite color. This makes it easy to detect whether any adjacent vertices have the same color.”

// ❓ Time Complexity?

// “The time complexity is O(V + E), because every vertex and every edge is processed at most a constant number of times. The space complexity is O(V) for the color array and BFS queue.”
