class Solution {

    public void dfs(int city, int[][] isConnected, boolean[] visited) {
        visited[city] = true;

        for (int i = 0; i < isConnected.length; i++) {
            if (isConnected[city][i] == 1 && !visited[i]) {
                dfs(i, isConnected, visited);
            }
        }
    }

public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, isConnected, visited);
                provinces++;
            }
        }

        return provinces;
    }
}


// class Solution {
//     public int findCircleNum(int[][] isConnected) {
//         int n = isConnected.length;
//         boolean[] vis = new boolean[n];

//         int count =0;
//         for(int i =0;i<n;i++){
//             if(!vis[i]){
//                 count++;
//                 dfs(isConnected,vis,i);
//             }
//         }
//         return count;
        
//     }
//     private void dfs(int[][]isConnected,boolean[] vis,int curr){
//         vis[curr] = true;
//         for(int j=0;j<isConnected.length;j++){
//             if(isConnected[curr][j] == 1 && !vis[j]){
//                 dfs(isConnected,vis,j);
//             }
//         }
//     }
// }


// // Interview me final 30-second ans
    
// // "I treat the cities as an undirected graph, where the matrix represents the edges. A province is a connected component. 
// // I maintain a visited array and iterate over every city. Whenever I find an unvisited city, I increment the province count and run DFS from that city.
// // DFS marks the entire connected component as visited, so it won't be counted again. Finally, I return the number of connected components.
// // The time complexity is O(n²) because we use an adjacency matrix, and the space complexity is O(n)."
