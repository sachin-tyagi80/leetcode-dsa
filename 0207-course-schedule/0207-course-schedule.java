class Solution {
    public boolean canFinish(int num, int[][] pre) {
        ArrayList<Integer>[] graph = new ArrayList[num];

        for(int i=0;i<num;i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] edge : pre){
            int u = edge[0];
            int v = edge[1];
            graph[v].add(u);

        }
        boolean[] vis = new boolean[num];
        boolean[] stack = new boolean[num];

        for(int i=0;i<num;i++){
            if(!vis[i]){
                if(dfs(i,graph,vis,stack)){
                    return false; // cycle exists
                }
            }

        }
        return true; // no cycle
        
    }
    private boolean dfs(int curr, ArrayList<Integer>[] graph,boolean[] vis, boolean[] stack){
        vis[curr] = true;
        stack[curr] = true;
        for(int neigh : graph[curr]){
            if(!vis[neigh]){
                if(dfs(neigh,graph,vis,stack)){
                    return true;
                }
            }
            else if(stack[neigh]){
                return true;
            }
        }
        stack[curr] = false;
        return false;
    }
}


// import java.util.*;

// class Solution {

//     public boolean canFinish(int numCourses, int[][] prerequisites) {

//         ArrayList<Integer>[] graph = new ArrayList[numCourses];

//         // Adjacency list
//         for (int i = 0; i < numCourses; i++) {
//             graph[i] = new ArrayList<>();
//         }

//         // Build graph
//         for (int[] edge : prerequisites) {

//             int course = edge[0];
//             int prerequisite = edge[1];

//             graph[prerequisite].add(course);
//         }

//         boolean[] visited = new boolean[numCourses];
//         boolean[] recStack = new boolean[numCourses];

//         // Check every component
//         for (int i = 0; i < numCourses; i++) {

//             if (!visited[i]) {

//                 if (dfs(i, graph, visited, recStack)) {
//                     return false; // cycle exists
//                 }
//             }
//         }

//         return true; // no cycle
//     }

//     private boolean dfs(
//             int curr,
//             ArrayList<Integer>[] graph,
//             boolean[] visited,
//             boolean[] recStack) {

//         visited[curr] = true;
//         recStack[curr] = true;

//         for (int neighbor : graph[curr]) {

//             // Not visited
//             if (!visited[neighbor]) {

//                 if (dfs(neighbor, graph, visited, recStack)) {
//                     return true;
//                 }
//             }

//             // Already in current DFS path
//             else if (recStack[neighbor]) {

//                 return true;
//             }
//         }

//         // Remove from current DFS path
//         recStack[curr] = false;

//         return false;
//     }
// }



// 🎤 Interview Explanation

// Interviewer agar bole "Explain your approach", tum ye bol sakte ho:
// "This problem can be modeled as a directed graph. Each course is a node, and if [a, b] is given, it means we have to complete b before a, so I create a directed edge from b to a."
// "The main observation is that if the prerequisite graph contains a cycle, it is impossible to finish all courses. For example, if 0 → 1 → 0, both courses depend on each other."
// "I use DFS with two boolean arrays: visited tells whether a node has been visited, and recStack tells whether the node is currently present in the DFS recursion path."
// "During DFS, if I find an unvisited neighbor, I recursively visit it. If I find a neighbor that is already in the current recursion stack, I have found a cycle."
// "If any cycle exists, I return false. Otherwise, all courses can be completed, so I return true."

// ⭐ Interviewer Follow-up Questions
// Q1. Why did you use DFS?
// Answer:
// "Because this is a directed graph cycle detection problem, and DFS with a recursion stack is a standard way to detect a back edge, which indicates a cycle."

// Q2. Why do you need recStack?
// Answer:
// "visited only tells me that a node was visited sometime earlier. recStack tells me whether that node belongs to the current DFS path. If I reach a node that is already in the current path, there is a cycle."
// Example:
// 0 → 1 → 2
// ↑       |
// └───────┘

// When 2 → 0 is found:
// recStack[0] == true
// So cycle exists.

// Q3. Why can't you use only visited[]?
// Answer:
// "Because a previously visited node doesn't necessarily mean there is a cycle. It could have been completely processed in another DFS path. We specifically need to know whether the node is in the current recursion path."
    
// Q4. What does [1,0] mean?
// Answer:
// "It means course 0 must be completed before course 1."
// So graph:
// 0 → 1
// Not:
// 1 → 0
    
// Q5. What happens if there is no prerequisite?
// Example:
// numCourses = 3
// prerequisites = []
// There are no edges and therefore no cycle.
// Answer:
// true
    
// Q6. What if the graph has multiple components?
// Example:
// 0 → 1
// 2 → 3 → 2
// There is a cycle in the second component.
// That's why we do:
// for (int i = 0; i < numCourses; i++) {
//     if (!visited[i]) {
//         if (dfs(i, graph, visited, recStack)) {
//             return false;
//         }
//     }
// }

// Answer:
// "I start DFS from every unvisited node so that disconnected components are also checked."

// Q7. Why do you set recStack[curr] = false?
// recStack[curr] = false;
// Answer:
// "Once DFS finishes processing the current node, it is no longer part of the current recursion path, so I remove it from recStack."

// Example:
// DFS path:
// 0 → 1 → 2
// After finishing 2:
// recStack:
// 0 → true
// 1 → true
// 2 → false
    
// Q8. What is the time complexity?
// O(V + E)
// Answer:
// "Every course is visited at most once, and every prerequisite edge is processed at most once, so the time complexity is O(V + E)."

// Q9. Space complexity?
// O(V + E)
// Because:
// Adjacency List → O(V + E)
// visited         → O(V)
// recStack        → O(V)
// DFS recursion   → O(V)

// Overall:

// O(V + E)
// 🔥 Q10. Can you solve it without DFS?
// Yes.
// Use BFS + Topological Sort (Kahn's Algorithm).
// Idea:
// indegree = number of prerequisites
// Courses having:
// indegree = 0
// can be taken first.
// Then remove them and reduce the indegree of their neighbors.
// At the end:
// processed courses == numCourses
//         ↓
//       true
// processed courses < numCourses
//         ↓
//       cycle
//         ↓
//       false

// This is a very common follow-up.

// 🔥 Q11. DFS vs BFS — which one would you choose?
// DFS	BFS
// Uses recursion	Uses Queue
// visited + recStack	indegree
// Direct cycle detection	Topological sorting
// O(V+E)	O(V+E)
// Interview me bol sakte ho:
// "Both are O(V + E). For this implementation I prefer DFS because the cycle detection logic is straightforward. BFS using Kahn's algorithm is also a good alternative."

// 🔥 Q12. What if recursion depth becomes very large?
// Ye Java me important follow-up hai.

// If:

// 0 → 1 → 2 → 3 → ... → 2000

// DFS recursion depth large ho sakti hai.

// Alternative:

// "I can use BFS/Kahn's algorithm with a queue to avoid recursive DFS."

