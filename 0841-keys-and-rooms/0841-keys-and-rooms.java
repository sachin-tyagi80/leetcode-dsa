class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] vis =  new boolean[n];
        dfs(0,rooms,vis);
        for(int i=0;i<n;i++){
            if(!vis[i]){
                return false;
            }

        }
        return true;
    }
    private void dfs(int curr,List<List<Integer>> rooms, boolean[] vis){
        vis[curr] = true;
        for(int key : rooms.get(curr)){
            if(!vis[key]){
                dfs(key,rooms,vis);
            }
        }
    }
}