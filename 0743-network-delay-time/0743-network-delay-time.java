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



////////////////////////////////////////////////////////////////////////////////////////////////
// 🎤 Interview Explanation
// 1. Problem ko kaise explain karna hai?

// “We are given a directed weighted graph and a source node k. We need to find the minimum time required for the signal to reach all nodes. If any node is unreachable, we return -1.”

// Hindi mein:

// Humein ek directed weighted graph diya hai aur signal k node se start hota hai. Humein har node tak minimum time find karna hai. Jo node unreachable ho, us case mein -1 return karna hai.

// 2. Which algorithm and why?

// “Since this is a weighted graph with non-negative edge weights, I use Dijkstra's algorithm to find the shortest distance from source k to every node.”

// Hindi:

// Graph weighted hai aur saare weights non-negative hain, isliye shortest path ke liye Dijkstra use karenge.

// 3. Graph kaise represent kiya?

// Code:

// List<int[]>[] graph = new ArrayList[n + 1];

// Interview mein:

// “I use an adjacency list to represent the graph because it is more space-efficient for a graph with a limited number of edges.”

// Example:

// 2 → 1 (1)
// 2 → 3 (1)
// 3 → 4 (1)
// 4. Distance array ka purpose?
// int[] dist = new int[n + 1];
// Arrays.fill(dist, Integer.MAX_VALUE);

// dist[source] = 0;

// Interview:

// “The dist array stores the shortest known distance from the source to every node. Initially, all distances are infinity except the source, whose distance is zero.”

// 5. PriorityQueue kyun?
// PriorityQueue<int[]> pq =
//         new PriorityQueue<>((a, b) -> a[1] - b[1]);

// Interview:

// “I use a min-priority queue so that the node with the smallest current distance is processed first.”

// Hindi:

// Har step par jis node ki distance sabse chhoti hai, usko pehle process karna hai. Isliye Min Heap/PriorityQueue use karte hain.

// 6. Dijkstra ka main process
// while (!pq.isEmpty()) {

//     int[] current = pq.poll();

//     int node = current[0];
//     int distance = current[1];

// Interview:

// “I repeatedly remove the node with the smallest distance from the priority queue and explore all of its neighbours.”

// 7. Relaxation explain karo ⭐

// Ye sabse important part hai:

// int newDistance = distance + weight;

// if (newDistance < dist[nextNode]) {

//     dist[nextNode] = newDistance;

//     pq.offer(new int[]{
//         nextNode,
//         newDistance
//     });
// }

// Interview:

// “For every neighbour, I calculate the new distance through the current node. If this new distance is smaller than the previously known distance, I update it and push the new distance into the priority queue. This process is called relaxation.”

// Simple Hindi:

// Current node tak distance
// +
// Edge ka weight
// =
// Neighbour tak new distance

// Agar new distance smaller hai → update.

// 8. Ye condition kyun?
// if (distance > dist[node]) {
//     continue;
// }

// Interview:

// “A node can be inserted into the priority queue multiple times with different distances. If the current distance is greater than the already known shortest distance, this entry is outdated, so I skip it.”

// Example:

// node 4 → distance 10
// node 4 → distance 5

// Agar 5 already mil gaya hai, to 10 ko process karne ki zarurat nahi.

// 9. Final answer kaise milta hai?

// Dijkstra ke baad:

// for (int i = 1; i <= n; i++) {

//     if (dist[i] == Integer.MAX_VALUE) {
//         return -1;
//     }

//     answer = Math.max(answer, dist[i]);
// }

// Interview:

// “After Dijkstra, if any node still has infinity distance, it means that node is unreachable, so I return -1. Otherwise, I return the maximum shortest distance because the signal needs to reach every node.”

// Why maximum?

// Suppose:

// dist = [1, 0, 1, 2]

// Signal:

// Node 1 → 1 sec
// Node 2 → 0 sec
// Node 3 → 1 sec
// Node 4 → 2 sec

// Sabhi nodes ko signal milne mein:

// max = 2 seconds

// So answer = 2.

// ⏱️ Complexity

// Let:

// V = number of nodes
// E = number of edges
// Time:
// O((V + E) log V)
// Space:
// O(V + E)

// Interview mein simply:

// “The time complexity is O((V + E) log V) using an adjacency list and priority queue, and the space complexity is O(V + E).”

// 🔥 Most Important Follow-up Questions
// Q1. Why Dijkstra?

// Answer:

// Because the graph is weighted and all edge weights are non-negative, so Dijkstra can find the shortest path efficiently.

// Q2. Why not BFS?

// Answer:

// BFS works for unweighted graphs or graphs where all edges have equal weight. Here edge weights can be different, so BFS cannot guarantee the shortest weighted path.

// Example:

// A → B = 10
// A → C = 1
// C → B = 1

// BFS may choose:

// A → B = 10

// But shortest path is:

// A → C → B = 2
// Q3. Can we use DFS?

// Answer:

// DFS can traverse the graph, but it does not naturally guarantee the shortest path in a weighted graph. We would need additional distance tracking and potentially explore many paths. Dijkstra is the appropriate shortest-path algorithm here.

// Q4. Can Dijkstra handle negative weights?

// Answer:

// No. Dijkstra is not suitable for negative edge weights. For negative weights, Bellman-Ford can be used.

// Q5. Why PriorityQueue?

// Answer:

// We need to repeatedly select the node with the smallest current distance. A min-priority queue does this efficiently.

// Q6. What is relaxation?

// Answer:

// Relaxation means checking whether reaching a node through the current node gives a shorter distance than the previously known distance.

// if (newDistance < dist[nextNode])
// Q7. Why do we return the maximum distance?

// Answer:

// Because the signal must reach all nodes. The node with the largest shortest distance is the last node to receive the signal.

// Q8. Why Integer.MAX_VALUE?

// Answer:

// It represents infinity, meaning that initially we don't know how to reach that node.

// Q9. Why n + 1?
// List<int[]>[] graph = new ArrayList[n + 1];

// Answer:

// Nodes are numbered from 1 to n, so using n + 1 allows us to directly use the node number as the array index.

// Q10. Why can the same node enter PriorityQueue multiple times?

// Answer:

// Suppose:

// A → B = 10
// A → C = 2
// C → B = 3

// Initially:

// dist[B] = 10

// Later:

// dist[B] = 5

// So both (B,10) and (B,5) may exist in the queue.

// That's why we use:

// if (distance > dist[node]) {
//     continue;
// }

// to ignore outdated entries.

// ⭐ 30-Second Interview Answer

// Agar interviewer bole "Explain your approach", ye bolo:

// “I use Dijkstra's algorithm because this is a directed weighted graph with non-negative edge weights. First, I represent the graph using an adjacency list. Then I initialize a distance array with infinity and set the source distance to zero. I use a min-priority queue to always process the node with the smallest known distance. For each neighbour, I perform relaxation by checking whether the new distance is smaller than the existing distance. After processing all nodes, if any node is unreachable I return -1; otherwise, I return the maximum shortest distance because the signal has to reach every node. The time complexity is O((V + E) log V) and space complexity is O(V + E).”

// 🧠 Pattern yaad rakho
// Directed Graph
//        +
// Weighted Graph
//        +
// Non-negative weights
//        ↓
// Shortest Path
//        ↓
// DIJKSTRA
//        ↓
// PriorityQueue
//        ↓
// Relaxation
//        ↓
// Maximum shortest distance

// 743 Network Delay Time = Dijkstra ka must-know interview problem.
