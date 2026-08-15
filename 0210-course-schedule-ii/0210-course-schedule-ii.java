import java.util.*;

class Solution {

    public int[] findOrder(int num, int[][] pre) {

        ArrayList<Integer>[] graph = new ArrayList[num];

        // Create graph
        for (int i = 0; i < num; i++) {
            graph[i] = new ArrayList<>();
        }

        // Build graph
        for (int[] edge : pre) {

            int u = edge[0];
            int v = edge[1];

            // prerequisite -> course
            graph[v].add(u);
        }

        boolean[] vis = new boolean[num];
        boolean[] stack = new boolean[num];

        Stack<Integer> st = new Stack<>();

        // DFS
        for (int i = 0; i < num; i++) {

            if (!vis[i]) {

                if (dfs(i, graph, vis, stack, st)) {

                    // Cycle exists
                    return new int[0];
                }
            }
        }

        // Topological order
        int[] ans = new int[num];

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

        vis[curr] = true;
        stack[curr] = true;

        for (int neigh : graph[curr]) {

            if (!vis[neigh]) {

                if (dfs(neigh, graph, vis, stack, st)) {
                    return true;
                }

            } else if (stack[neigh]) {

                // Cycle exists
                return true;
            }
        }

        // Current DFS path se bahar
        stack[curr] = false;

        // Topological sorting
        st.push(curr);

        return false;
    }
}