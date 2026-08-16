class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] vis = new boolean[n];

        int count =0;
        for(int i =0;i<n;i++){
            if(!vis[i]){
                count++;
                dfs(isConnected,vis,i);
            }
        }
        return count;
        
    }
    private void dfs(int[][]isConnected,boolean[] vis,int curr){
        vis[curr] = true;
        for(int j=0;j<isConnected.length;j++){
            if(isConnected[curr][j] == 1 && !vis[j]){
                dfs(isConnected,vis,j);
            }
        }
    }
}