class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] edge : dislikes){
            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        int[] color = new int[n+1];
        Arrays.fill(color, -1);

        for(int i =1;i<=n;i++){
            if(color[i] == -1){
                color[i] = 0;
                if(!dfs(i,graph,color)){
                    return false;
                }
            }
        }
        return true;
        
    }
    private boolean dfs(int curr, ArrayList<Integer>[] graph,int[] color){
        for(int neighbor : graph[curr]){
            if(color[neighbor] == -1){
                color[neighbor] = 1 - color[curr];
                if(!dfs(neighbor,graph,color)){
                    return false;
                }
            }
            else if(color[neighbor] == color[curr]){
                return false;
            }
        }
        return true;
    }
}

// Interview mein kaise explain karna hai 🎤
// Interviewer: "Explain your approach."
// "This is a bipartite graph problem. I represent each person as a vertex and each dislike relationship as an undirected edge. I use two colors to represent the two groups. For every uncolored vertex, I start DFS and assign it color 0. Every neighbor must get the opposite color. If I ever find an edge whose two endpoints have the same color, then the graph is not bipartite and I return false. Otherwise, after processing all components, I return true."

// 8. Important interview follow-ups
// Q1. Why do we use an undirected graph?
// Because dislike relationship means:
// 1 dislikes 2
// and for grouping purposes, 1 and 2 cannot be together, regardless of direction.
// So we add:
// graph[u].add(v);
// graph[v].add(u);

// Q2. Why 1 - color[curr]?
// Because only two colors hain:
// 0 → 1
// 1 → 0
// So:
// color[neighbor] = 1 - color[curr];
// automatically opposite color deta hai.

// Q3. What if the graph has multiple components?
// That's why:
// for (int i = 1; i <= n; i++) {
//     if (color[i] == -1) {
//         ...
//     }
// }
// Har uncolored person se DFS start karte hain.

// Q4. BFS se kar sakte hain?
// Yes.
// Same coloring concept:
// DFS → recursion
// BFS → Queue
// Dono ka result same hoga.

// Q5. How do you know a graph is bipartite?
// A graph is bipartite if:
// It can be colored using 2 colors such that no edge connects two vertices having the same color.

// Shortcut:

// 2-colorable = Bipartite
// Q6. Time Complexity?
// Graph banane mein:
// O(E)
// DFS mein har vertex/edge process:
// O(V + E)
// Total:
// Time  = O(V + E)
// Space = O(V + E)
// where V = n and E = dislikes.length.
// Connection with your previous question
// Tumne abhi 785. Is Graph Bipartite? padha tha.

// 785 and 886 ka core algorithm same hai:
// 785 → Given graph → check bipartite
// 886 → Dislikes → first graph banao → check bipartite
// So 886 ko 785 ka real-world version samajh sakte ho.
//////////////////////////////////////////////////////////////////////////////////////////////////
// import java.util.*;

// class Solution {

//     public boolean possibleBipartition(int n, int[][] dislikes) {

//         // Create graph
//         ArrayList<Integer>[] graph = new ArrayList[n + 1];

//         for (int i = 1; i <= n; i++) {
//             graph[i] = new ArrayList<>();
//         }

//         // Build undirected graph
//         for (int[] edge : dislikes) {

//             int u = edge[0];
//             int v = edge[1];

//             graph[u].add(v);
//             graph[v].add(u);
//         }

//         // -1 = not colored
//         //  0 = Group 0
//         //  1 = Group 1
//         int[] color = new int[n + 1];

//         Arrays.fill(color, -1);

//         // Graph can have multiple components
//         for (int i = 1; i <= n; i++) {

//             if (color[i] == -1) {

//                 if (!bfs(i, graph, color)) {
//                     return false;
//                 }
//             }
//         }

//         return true;
//     }

//     private boolean bfs(
//             int start,
//             ArrayList<Integer>[] graph,
//             int[] color) {

//         Queue<Integer> queue = new LinkedList<>();

//         // Start node ko Group 0
//         color[start] = 0;
//         queue.add(start);

//         while (!queue.isEmpty()) {

//             int curr = queue.remove();

//             for (int neighbor : graph[curr]) {

//                 // Neighbor not colored
//                 if (color[neighbor] == -1) {

//                     // Opposite group
//                     color[neighbor] = 1 - color[curr];

//                     queue.add(neighbor);
//                 }

//                 // Same group -> invalid
//                 else if (color[neighbor] == color[curr]) {

//                     return false;
//                 }
//             }
//         }

//         return true;
//     }
// }

// 🎤 Interview Explanation
// “This problem is a Bipartite Graph problem. We need to divide all people into two groups such that people who dislike each other must belong to different groups.”
// “I model each person as a graph node and each dislike relationship as an undirected edge.”
// “Then I use BFS with 2-coloring. I maintain a color array where -1 means uncolored, 0 means Group 0 and 1 means Group 1.”
// “For every uncolored node, I start BFS and assign it color 0. For each neighbor, I assign the opposite color using 1 - color[curr].”
// “If a neighbor is already colored and has the same color as the current node, then both endpoints of an edge are in the same group, so the graph is not bipartite and I return false.”
// “Because the graph can have multiple disconnected components, I run BFS for every uncolored node.”
// “If no conflict is found, I return true.”
