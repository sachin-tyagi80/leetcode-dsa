class Solution {
    public boolean canFinish(int num, int[][] pre) {
        ArrayList<Integer>[] graph = new ArrayList[num];

        for(int i=0;i<num;i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] edge : pre){
            int u = edge[0];
            int v = edge[1];
            graph[v].add(u);

        }
        boolean[] vis = new boolean[num];
        boolean[] stack = new boolean[num];

        for(int i=0;i<num;i++){
            if(!vis[i]){
                if(dfs(i,graph,vis,stack)){
                    return false; // cycle exists
                }
            }

        }
        return true; // no cycle
        
    }
    private boolean dfs(int curr, ArrayList<Integer>[] graph,boolean[] vis, boolean[] stack){
        vis[curr] = true;
        stack[curr] = true;
        for(int neigh : graph[curr]){
            if(!vis[neigh]){
                if(dfs(neigh,graph,vis,stack)){
                    return true;
                }
            }
            else if(stack[neigh]){
                return true;
            }
        }
        stack[curr] = false;
        return false;
    }
}