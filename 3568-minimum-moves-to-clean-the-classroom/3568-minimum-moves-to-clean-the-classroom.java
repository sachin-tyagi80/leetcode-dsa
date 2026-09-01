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


// Interview Explanation — Short Version

// Interview mein aise explain karna:

// "This is a shortest path problem because every movement costs exactly one. So I will use BFS. However, the state cannot be represented only by the cell position because remaining energy and already collected litter affect future decisions. Since there are at most 10 litter cells, I can represent the collected litter using a bitmask. Therefore my BFS state is (row, column, remainingEnergy, mask). Whenever I enter a litter cell, I set its corresponding bit. Whenever I enter an R cell, I reset the energy to the maximum. The first state where the mask contains all litter is the minimum number of moves. If BFS finishes without reaching it, I return -1."

// Ye interview mein kaafi strong explanation hai.

// 16. Interview Follow-up Questions
// Q1. Why BFS instead of DFS?

// Answer:

// Every move has equal cost of 1, so BFS guarantees the shortest number of moves. DFS may find a valid solution but not necessarily the minimum one.

// Q2. Why can't you use only visited[row][col]?

// Answer:

// Because reaching the same cell with different remaining energy or different collected litter produces different future possibilities.

// Q3. Why use bitmask?

// Answer:

// There are at most 10 litter cells. A bitmask can represent all collected/uncollected combinations using only 2^10 = 1024 possibilities.

// Q4. What does mask | (1 << index) do?

// Answer:

// It sets the bit corresponding to the newly collected litter to 1.

// Q5. What does (1 << litterCount) - 1 mean?

// Answer:

// It creates a mask where all litter bits are 1. For example, for 3 litters, it gives 111.

// Q6. Why don't we need to modify the grid when collecting litter?

// Because we track collected litter using:

// mask

// So we don't need to change:

// 'L' → '.'
// Q7. What happens when energy becomes zero?

// After reaching a cell with zero energy, the student can continue only if that cell is R.

// In our transition, when entering R:

// newEnergy = energy;

// So it gets restored.

// Q8. Why can the student not move when currEnergy == 0?

// Because every movement costs 1 energy.

// if (currEnergy == 0) {
//     continue;
// }

// So they need to already be on a reset cell for their energy to have been restored.

// Q9. Why is the complexity exponential?

// Because of the litter mask:

// 2^L

// possible combinations exist.

// But L <= 10, so:

// 2^10 = 1024

// which is manageable.

// Q10. Can we use DFS + memoization?

// Yes.

// You could define:

// dp(row, col, energy, mask)

// but because the question asks for a minimum number of moves, BFS is more natural and directly gives the shortest path.

// ⭐ Interview mein yaad rakhne wali line

// Is problem ka main pattern:

// Grid shortest path
//         +
// Energy as state
//         +
// Multiple items
//         +
// Bitmask
//         +
// BFS

// Final state:

// (row, col, energy, mask)

// Ye pattern samajh gaya to similar grid + keys/items + limited resources + minimum moves problems bhi easily identify kar paoge.
