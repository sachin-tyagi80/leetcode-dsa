import java.util.*;

class Solution {

    public int countCompleteComponents(int n, int[][] edges) {

        ArrayList<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {

            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        boolean[] visited = new boolean[n];

        int count = 0;

        for (int i = 0; i < n; i++) {

            if (!visited[i]) {

                int[] result = dfs(i, graph, visited);

                int vertices = result[0];
                int edgesCount = result[1] / 2;

                int requiredEdges =
                        vertices * (vertices - 1) / 2;

                if (edgesCount == requiredEdges) {
                    count++;
                }
            }
        }

        return count;
    }

    private int[] dfs(
            int curr,
            ArrayList<Integer>[] graph,
            boolean[] visited) {

        visited[curr] = true;

        int vertices = 1;
        int edges = graph[curr].size();

        for (int neighbor : graph[curr]) {

            if (!visited[neighbor]) {

                int[] result =
                        dfs(neighbor, graph, visited);

                vertices += result[0];
                edges += result[1];
            }
        }

        return new int[]{vertices, edges};
    }
}


// Interview mein kaise explain karna hai?

// English:

// First, I build an adjacency list for the undirected graph. Then I run DFS from every unvisited vertex to find each connected component. During DFS, I count the number of vertices and the total degree of that component. Since every undirected edge is counted twice in the degree sum, I divide it by 2 to get the actual number of edges. For a component with k vertices to be complete, it must contain exactly k * (k - 1) / 2 edges. If the actual edge count matches this value, I increment the answer.

// Hindi:

// Pehle adjacency list banayenge. Phir har unvisited vertex se DFS chalayenge aur ek complete connected component ke saare vertices find karenge. DFS ke during vertices aur edges count karenge. Undirected graph mein har edge dono directions mein stored hoti hai, isliye degree sum ko 2 se divide karenge. Agar k vertices hain, to complete graph mein k*(k-1)/2 edges honi chahiye. Agar actual edges itni hi hain, to answer increase karenge.


// Follow-up questions interviewer pooch sakta hai
// Q1. Why divide edge count by 2?

// Because graph is undirected.

// 0 -- 1

// Adjacency list mein:

// 0 → 1
// 1 → 0

// Same edge 2 times count hoti hai.

// Q2. Complete graph ki maximum edges ka formula?
// k * (k - 1) / 2

// Because every pair of vertices has exactly one edge.

// Q3. DFS ki jagah BFS use kar sakte ho?

// Yes.

// Same logic:

// BFS
//  ↓
// component vertices count
//  ↓
// edge count
//  ↓
// k*(k-1)/2
//  ↓
// complete?
// Q4. DSU se solve kar sakte ho?

// Yes.

// Ye problem DFS/BFS + DSU dono se solve ho sakti hai.

// Lekin tumhare current graph practice ke liye:

// DFS approach easiest to explain.

// Q5. Complexity?

// Graph construction:

// O(E)

// DFS:

// O(V + E)

// Total:

// Time = O(V + E)
// Space = O(V + E)
// Important interview connection

// Is question ko dekhte hi:

// Connected Components
//         ↓
// DFS / BFS
//         ↓
// Component ke vertices count
//         ↓
// Component ki edges count
//         ↓
// Complete Graph Formula
// k(k-1)/2
