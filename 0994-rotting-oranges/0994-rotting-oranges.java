class Solution {

    public int orangesRotting(int[][] grid) {

        Queue<int[]> queue = new LinkedList<>();

        int fresh = 0;

        int rows = grid.length;
        int cols = grid[0].length;

        // Directions
        int[][] dir = {
                {-1,0},
                {1,0},
                {0,-1},
                {0,1}
        };

        // Find all rotten oranges
        for(int i=0;i<rows;i++){

            for(int j=0;j<cols;j++){

                if(grid[i][j]==2){

                    queue.offer(new int[]{i,j});
                }

                else if(grid[i][j]==1){

                    fresh++;
                }
            }
        }

        if(fresh==0)
            return 0;

        int minutes=0;

        while(!queue.isEmpty() && fresh>0){

            int size=queue.size();

            for(int i=0;i<size;i++){

                int[] curr=queue.poll();

                for(int[] d:dir){

                    int nr=curr[0]+d[0];
                    int nc=curr[1]+d[1];

                    if(nr>=0 && nc>=0 &&
                       nr<rows && nc<cols &&
                       grid[nr][nc]==1){

                        grid[nr][nc]=2;

                        fresh--;

                        queue.offer(new int[]{nr,nc});
                    }
                }
            }

            minutes++;
        }

        return fresh==0 ? minutes : -1;
    }
}