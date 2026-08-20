class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        path.add(0);
        dfs(0,graph,path,res);
        return res;
    }

    private void dfs(int src,int[][] graph,List<Integer> path,List<List<Integer>> res){
        if(src == graph.length-1){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int nigh : graph[src]){
            path.add(nigh);
            dfs(nigh,graph,path,res);

            path.remove(path.size() -1);
        }
    }
}