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