import java.util.*;

class Solution {

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        ArrayList<Integer>[] graph = new ArrayList[numCourses];

        // Create graph
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        // Build graph
        for (int[] edge : prerequisites) {

            int course = edge[0];
            int prerequisite = edge[1];

            // prerequisite -> course
            graph[prerequisite].add(course);
        }

        boolean[] vis = new boolean[numCourses];
        boolean[] stack = new boolean[numCourses];

        Stack<Integer> st = new Stack<>();

        // DFS for every component
        for (int i = 0; i < numCourses; i++) {

            if (!vis[i]) {

                if (dfs(i, graph, vis, stack, st)) {

                    // Cycle exists
                    return new int[0];
                }
            }
        }

        // Topological order
        int[] ans = new int[numCourses];

        int index = 0;

        while (!st.isEmpty()) {
            ans[index++] = st.pop();
        }

        return ans;
    }

    private boolean dfs(
            int curr,
            ArrayList<Integer>[] graph,
            boolean[] vis,
            boolean[] stack,
            Stack<Integer> st) {

        // Mark visited
        vis[curr] = true;

        // Put in current DFS path
        stack[curr] = true;

        // Visit neighbours
        for (int neigh : graph[curr]) {

            // Not visited
            if (!vis[neigh]) {

                if (dfs(neigh, graph, vis, stack, st)) {
                    return true;
                }

            }

            // Already in current DFS path
            else if (stack[neigh]) {

                return true;
            }
        }

        // Remove from current DFS path
        stack[curr] = false;

        // Add to topological stack
        st.push(curr);

        return false;
    }
}


// 🎯 Interview mein short explanation

// "I first build a directed graph from prerequisite to course. Then I use DFS. vis[] tracks already visited nodes, while stack[] tracks nodes in the current DFS recursion path. If I find an already visited neighbour that is also present in the current recursion path, a cycle exists, so no valid ordering is possible. Otherwise, after processing all neighbours, I push the current node into a stack. Finally, I pop all nodes from the stack to get the topological ordering."

// Time Complexity: O(V + E)
// Space Complexity: O(V + E)

////////////////////////////////////////////////////////////////////
// import java.util.*;

// class Solution {

//     public int[] findOrder(int numCourses, int[][] prerequisites) {

//         ArrayList<Integer>[] graph = new ArrayList[numCourses];

//         for (int i = 0; i < numCourses; i++) {
//             graph[i] = new ArrayList<>();
//         }

//         int[] indegree = new int[numCourses];

//         // Build graph
//         for (int[] edge : prerequisites) {

//             int course = edge[0];
//             int prerequisite = edge[1];

//             graph[prerequisite].add(course);

//             indegree[course]++;
//         }

//         return bfs(graph, indegree, numCourses);
//     }


//     public int[] bfs(ArrayList<Integer>[] graph,
//                      int[] indegree,
//                      int numCourses) {

//         Queue<Integer> q = new LinkedList<>();

//         // Indegree 0 courses
//         for (int i = 0; i < numCourses; i++) {

//             if (indegree[i] == 0) {
//                 q.add(i);
//             }
//         }

//         int[] ans = new int[numCourses];
//         int index = 0;

//         while (!q.isEmpty()) {

//             int curr = q.remove();

//             ans[index++] = curr;

//             for (int neigh : graph[curr]) {

//                 indegree[neigh]--;

//                 if (indegree[neigh] == 0) {
//                     q.add(neigh);
//                 }
//             }
//         }

//         // Cycle exists
//         if (index != numCourses) {
//             return new int[0];
//         }

//         return ans;
//     }
// }


// // English interview answer:

// // I will model the courses as a directed graph. For every prerequisite [course, prerequisite], I create an edge from prerequisite to course. 
// // Then I calculate the indegree of every course. I use Kahn's Algorithm, which is a BFS-based topological sorting algorithm. I put all courses with indegree 
// // zero into a queue. After processing a course, I decrease the indegree of its neighbours. If a neighbour's indegree becomes zero, I add it to the queue.
// // If I process all courses, I return the ordering. Otherwise, a cycle exists and I return an empty array.


// // 🎯 Interview Follow-up Questions
// // Q1. Why are you using BFS?

// // Answer:

// // I am using Kahn's Algorithm, which is a BFS-based approach for topological sorting.

// // Q2. What is indegree?

// // Indegree is the number of incoming edges to a vertex.

// // In this problem:

// // It represents the number of prerequisites that are still pending for a course.

// // Q3. How do you detect a cycle?

// // If a cycle exists, the nodes inside the cycle will never have indegree zero, so they will never enter the queue. Therefore, if the number of processed courses is less than numCourses, a cycle exists.

// // if (index != numCourses)
// //     return new int[0];
// // Q4. Why graph[prerequisite].add(course)?

// // Because:

// // [course, prerequisite]

// // means:

// // prerequisite → course

// // Example:

// // [2,0]

// // means:

// // 0 → 2
// // Q5. Why indegree[course]++?

// // Because the course has one more prerequisite/incoming edge.

// // 0 → 2

// // So:

// // indegree[2]++
// // Q6. What if multiple courses have indegree 0?

// // Any of them can be processed first.

// // Therefore multiple valid answers can exist.

// // For example:

// // [0,1,2,3]

// // and

// // [0,2,1,3]

// // both can be valid.
