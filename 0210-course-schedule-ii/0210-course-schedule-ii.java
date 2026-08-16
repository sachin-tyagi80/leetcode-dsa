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
