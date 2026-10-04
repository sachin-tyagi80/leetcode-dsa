class Solution {

    public int networkDelayTime(int[][] times, int n, int k) {

        // Create adjacency list
        List<int[]>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Add edges
        for (int[] time : times) {
            int u = time[0];
            int v = time[1];
            int w = time[2];

            graph[u].add(new int[]{v, w});
        }

        // Run Dijkstra
        int[] dist = dijkstra(graph, n, k);

        // Find maximum distance
        int answer = 0;

        for (int i = 1; i <= n; i++) {

            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }

            answer = Math.max(answer, dist[i]);
        }

        return answer;
    }


    // Dijkstra function
    public int[] dijkstra(List<int[]>[] graph, int n, int source) {

        int[] dist = new int[n + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[source] = 0;

        // Min Heap
        PriorityQueue<int[]> pq =
                new PriorityQueue<>((a, b) -> a[1] - b[1]);

        pq.offer(new int[]{source, 0});

        while (!pq.isEmpty()) {

            int[] current = pq.poll();

            int node = current[0];
            int distance = current[1];

            // Ignore old distance
            if (distance > dist[node]) {
                continue;
            }

            // Visit neighbours
            for (int[] edge : graph[node]) {

                int nextNode = edge[0];
                int weight = edge[1];

                int newDistance = distance + weight;

                // Relaxation
                if (newDistance < dist[nextNode]) {

                    dist[nextNode] = newDistance;

                    pq.offer(new int[]{
                            nextNode,
                            newDistance
                    });
                }
            }
        }

        return dist;
    }
}
