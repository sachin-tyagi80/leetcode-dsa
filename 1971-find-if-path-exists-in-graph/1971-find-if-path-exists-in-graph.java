class Solution {

    public boolean validPath(int n, int[][] edges, int source, int destination) {

        // Adjacency list
        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Build graph
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        boolean[] visited = new boolean[n];

        return dfs(source, destination, graph, visited);
    }

    private boolean dfs(int node, int destination,
                        List<Integer>[] graph,
                        boolean[] visited) {

        // Destination found
        if (node == destination) {
            return true;
        }

        visited[node] = true;

        for (int neighbor : graph[node]) {

            if (!visited[neighbor]) {

                if (dfs(neighbor, destination, graph, visited)) {
                    return true;
                }
            }
        }

        return false;
    }
}