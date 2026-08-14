class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] edge : dislikes){
            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        int[] color = new int[n+1];
        Arrays.fill(color, -1);

        for(int i =1;i<=n;i++){
            if(color[i] == -1){
                color[i] = 0;
                if(!dfs(i,graph,color)){
                    return false;
                }
            }
        }
        return true;
        
    }
    private boolean dfs(int curr, ArrayList<Integer>[] graph,int[] color){
        for(int neighbor : graph[curr]){
            if(color[neighbor] == -1){
                color[neighbor] = 1 - color[curr];
                if(!dfs(neighbor,graph,color)){
                    return false;
                }
            }
            else if(color[neighbor] == color[curr]){
                return false;
            }
        }
        return true;
    }
}