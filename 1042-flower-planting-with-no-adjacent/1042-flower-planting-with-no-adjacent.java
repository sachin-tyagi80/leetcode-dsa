import java.util.*;

class Solution {

    public int[] gardenNoAdj(int n, int[][] paths) {

        // Adjacency list
        ArrayList<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Build graph
        for (int[] path : paths) {

            int u = path[0] - 1;
            int v = path[1] - 1;

            graph[u].add(v);
            graph[v].add(u);
        }

        // color[i] = flower type of garden i
        int[] color = new int[n];

        // Process every garden
        for (int i = 0; i < n; i++) {

            boolean[] used = new boolean[5];

            // Check colors used by neighbors
            for (int neighbor : graph[i]) {

                if (color[neighbor] != 0) {
                    used[color[neighbor]] = true;
                }
            }

            // Choose first available flower
            for (int flower = 1; flower <= 4; flower++) {

                if (!used[flower]) {
                    color[i] = flower;
                    break;
                }
            }
        }

        return color;
    }
}