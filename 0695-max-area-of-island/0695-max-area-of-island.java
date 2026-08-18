class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int r = grid.length;
        int c =grid[0].length;
        int maxArea = 0;
        for(int i =0;i<r;i++){
            for(int j=0;j<c;j++){
                if(grid[i][j] == 1){
                    int area = dfs(grid,i,j);
                    maxArea = Math.max(maxArea,area);
                }
            }
        }
        return maxArea;
        
    }
    private int dfs(int[][] grid,int r,int c){
        if(r<0 || r>=grid.length || c<0 || c>=grid[0].length){
            return 0;
        }

        if(grid[r][c] == 0){
            return 0;
        }

        grid[r][c] = 0;
        
        int area   = 1;

        area += dfs(grid,r-1,c);
        area += dfs(grid,r+1,c);
        area += dfs(grid,r,c-1);
        area += dfs(grid,r,c+1);

        return area;
    }
}