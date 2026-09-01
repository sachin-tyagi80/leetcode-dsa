import java.util.*;

class Solution {

    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int startRow = 0;
        int startCol = 0;

        // Store litter position -> litter index
        int[][] litterIndex = new int[m][n];

        for (int[] row : litterIndex) {
            Arrays.fill(row, -1);
        }

        int litterCount = 0;

        // Find starting position and assign index to every litter
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startRow = i;
                    startCol = j;
                }

                if (ch == 'L') {
                    litterIndex[i][j] = litterCount;
                    litterCount++;
                }
            }
        }

        // No litter
        if (litterCount == 0) {
            return 0;
        }

        // If there are k litters, allMask = 111...111
        int allMask = (1 << litterCount) - 1;

        /*
         * visited[row][col][energy][mask]
         *
         * mask tells which litters are already collected.
         */
        boolean[][][][] visited =
                new boolean[m][n][energy + 1][1 << litterCount];

        // Queue stores:
        // {row, col, remainingEnergy, mask}
        Queue<int[]> queue = new LinkedList<>();

        // Starting state
        queue.offer(new int[]{
                startRow,
                startCol,
                energy,
                0
        });

        visited[startRow][startCol][energy][0] = true;

        int[][] directions = {
                {-1, 0},  // up
                {1, 0},   // down
                {0, -1},  // left
                {0, 1}    // right
        };

        int moves = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            // Process one BFS level
            while (size-- > 0) {

                int[] current = queue.poll();

                int row = current[0];
                int col = current[1];
                int currEnergy = current[2];
                int mask = current[3];

                // All litter collected
                if (mask == allMask) {
                    return moves;
                }

                for (int[] dir : directions) {

                    int newRow = row + dir[0];
                    int newCol = col + dir[1];

                    // Boundary check
                    if (newRow < 0 || newRow >= m ||
                        newCol < 0 || newCol >= n) {
                        continue;
                    }

                    // Obstacle
                    if (classroom[newRow].charAt(newCol) == 'X') {
                        continue;
                    }

                    // Need one energy to make this move
                    if (currEnergy == 0) {
                        continue;
                    }

                    int newEnergy = currEnergy - 1;
                    int newMask = mask;

                    char cell = classroom[newRow].charAt(newCol);

                    // Collect litter
                    if (cell == 'L') {

                        int index = litterIndex[newRow][newCol];

                        newMask = newMask | (1 << index);
                    }

                    // Reset energy
                    if (cell == 'R') {
                        newEnergy = energy;
                    }

                    // If this complete state is new
                    if (!visited[newRow][newCol]
                                      [newEnergy]
                                      [newMask]) {

                        visited[newRow][newCol]
                               [newEnergy]
                               [newMask] = true;

                        queue.offer(new int[]{
                                newRow,
                                newCol,
                                newEnergy,
                                newMask
                        });
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}