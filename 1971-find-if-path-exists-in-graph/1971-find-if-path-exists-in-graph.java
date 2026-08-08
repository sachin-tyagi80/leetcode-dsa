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


// Interview Explanation — English

// "I will use DFS to check whether there is a path from the source to the destination. First, I build an adjacency list because the graph is bidirectional,
// so for every edge [u,v], I add v to u's list and u to v's list. Then I start DFS from the source and maintain a visited array to avoid cycles. 
// If I reach the destination, I return true. If DFS finishes without reaching the destination, I return false."
