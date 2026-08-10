class Solution {

    public boolean isBipartite(int[][] graph) {

        int n = graph.length;

        // -1 means no color
        int[] color = new int[n];

        Arrays.fill(color, -1);

        // Check every component
        for (int i = 0; i < n; i++) {

            if (color[i] == -1) {

                // Give starting node color 0
                color[i] = 0;

                Queue<Integer> q = new LinkedList<>();
                q.add(i);

                while (!q.isEmpty()) {

                    int curr = q.remove();

                    // Check all neighbors
                    for (int neighbor : graph[curr]) {

                        // Neighbor is not colored
                        if (color[neighbor] == -1) {

                            // Give opposite color
                            color[neighbor] = 1 - color[curr];

                            q.add(neighbor);
                        }

                        // Neighbor already colored
                        else if (color[neighbor] == color[curr]) {

                            // Same color connected by an edge
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}