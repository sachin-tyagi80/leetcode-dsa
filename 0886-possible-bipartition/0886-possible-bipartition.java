import java.util.*;

class Solution {

    public boolean possibleBipartition(int n, int[][] dislikes) {

        // Create graph
        ArrayList<Integer>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Build undirected graph
        for (int[] edge : dislikes) {

            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        // -1 = not colored
        //  0 = Group 0
        //  1 = Group 1
        int[] color = new int[n + 1];

        Arrays.fill(color, -1);

        // Graph can have multiple components
        for (int i = 1; i <= n; i++) {

            if (color[i] == -1) {

                if (!bfs(i, graph, color)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean bfs(
            int start,
            ArrayList<Integer>[] graph,
            int[] color) {

        Queue<Integer> queue = new LinkedList<>();

        // Start node ko Group 0
        color[start] = 0;
        queue.add(start);

        while (!queue.isEmpty()) {

            int curr = queue.remove();

            for (int neighbor : graph[curr]) {

                // Neighbor not colored
                if (color[neighbor] == -1) {

                    // Opposite group
                    color[neighbor] = 1 - color[curr];

                    queue.add(neighbor);
                }

                // Same group -> invalid
                else if (color[neighbor] == color[curr]) {

                    return false;
                }
            }
        }

        return true;
    }
}