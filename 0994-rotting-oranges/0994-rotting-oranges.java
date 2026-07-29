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


// Why Multi-Source BFS?

// Interviewer: Why do we insert all rotten oranges into the queue initially?

// Answer:

// Because all rotten oranges start spreading at the same time. Treating every rotten orange as a BFS source simulates the infection correctly and computes the minimum time.

// Interview Explanation (English)

// "I solve this problem using Multi-Source BFS. First, I add all initially rotten oranges to the queue and count the fresh oranges. Then I perform BFS level by level, where each level represents one minute. During each level, every rotten orange infects its four adjacent fresh oranges, which are added to the queue for the next minute. If all fresh oranges become rotten, I return the total minutes; otherwise, I return -1."

// Common Interview Questions
// 1. Why BFS and not DFS?

// Because the infection spreads simultaneously from all rotten oranges. BFS processes nodes level by level, where each level naturally represents one minute. DFS cannot guarantee the minimum time.

// 2. Why use Multi-Source BFS?

// Because there can be multiple rotten oranges initially, and all of them start infecting neighbors at the same time.

// 3. Why count fresh oranges?

// To know when all oranges have become rotten and to detect impossible cases where some fresh oranges remain unreachable.

// 4. Why increment minutes after each level?

// Each BFS level corresponds to one minute of infection spreading.
