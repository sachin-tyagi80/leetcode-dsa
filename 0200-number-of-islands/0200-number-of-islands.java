class Solution {

    public int numIslands(char[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        int count = 0;

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                // New island found
                if (grid[i][j] == '1') {

                    count++;

                    dfs(grid, i, j);
                }
            }
        }

        return count;
    }

    private void dfs(char[][] grid, int row, int col) {

        // Out of boundary
        if (row < 0 || row >= grid.length ||
            col < 0 || col >= grid[0].length) {
            return;
        }

        // Water or already visited
        if (grid[row][col] == '0') {
            return;
        }

        // Mark as visited
        grid[row][col] = '0';

        // Up
        dfs(grid, row - 1, col);

        // Down
        dfs(grid, row + 1, col);

        // Left
        dfs(grid, row, col - 1);

        // Right
        dfs(grid, row, col + 1);
    }
}

// 🎤 Best Interview Explanation
// Agar interviewer kahe "Explain your approach", ye bolo:
// "I will use DFS to find connected components in the grid. I iterate through every cell, and whenever I find an unvisited land cell, I increment the island count and start DFS from that cell. The DFS visits all horizontally and vertically connected land cells and marks them as visited by changing them to water. Therefore, each DFS call represents exactly one island."

// Hindi mein iska meaning:
// "Main grid ko connected components ki tarah treat kar raha hoon. Har '1' jo abhi visited nahi hua hai, ek new island represent karta hai. Main count increase karke DFS karta hoon aur us island ke saare connected cells ko visited mark kar deta hoon."

// 🔥 Interview Follow-up Questions
// Q1. Why do you use DFS?
// Answer:
// DFS is useful for exploring the complete connected component starting from one land cell.

// Hindi:
// Ek land cell se connected poora island ek hi DFS se visit ho jata hai.

// Q2. Can you solve it using BFS?
// Answer:
// Yes. Instead of recursion, I can use a queue and perform BFS in the four directions.

// Q3. Why are there only four directions?
// Answer:
// Because the problem considers only horizontal and vertical connections. Diagonal cells are not considered connected.

// Q4. Can you avoid modifying the input grid?
// Answer:
// Yes. We can create a boolean[][] visited array and mark cells as visited there instead of changing '1' to '0'.

// Q5. What if diagonal connection is also considered?
// Then we would check 8 directions instead of 4:
// ↖ ↑ ↗
// ← X →
// ↙ ↓ ↘
    
// Q6. What is the time complexity?
// Time = O(m × n)
// Why?
// Har cell maximum ek baar DFS se visit hota hai.

// Q7. Space complexity?
// Using current code:
// Space = O(m × n)
// Worst case mein recursion stack m × n tak ja sakta hai.
// Agar visited[][] use karenge, to uska bhi O(m × n) space lagega.

// Q8. What if the grid is extremely large?
// Interviewer recursion stack overflow ka issue pooch sakta hai.
// Answer:
// Instead of recursive DFS, I would use iterative BFS/DFS with a queue or stack.

// Q9. Is this a graph problem?
// Answer:
// Yes. Each land cell can be treated as a graph node, and adjacent land cells are connected by edges. We are basically counting connected components.
// Ye tumhare previous graph questions se directly connected hai:
// Normal Graph:
// node → neighbors → DFS


// Number of Islands:
// cell → 4 neighboring cells → DFS
// Main pattern yaad rakho:
// New unvisited land → count++ → DFS/BFS → complete island mark visited.


///////////////////////////////////////////////////////////////
////Bas ek caveat: Agar interviewer kahe "Input grid ko modify nahi kar sakte", tab boolean[][] vis use karna.
// class Solution {

//     public int numIslands(char[][] grid) {

//         int rows = grid.length;
//         int cols = grid[0].length;

//         boolean[][] vis = new boolean[rows][cols];

//         int count = 0;

//         for (int i = 0; i < rows; i++) {

//             for (int j = 0; j < cols; j++) {

//                 // Land + not visited = new island
//                 if (grid[i][j] == '1' && !vis[i][j]) {

//                     count++;

//                     dfs(grid, vis, i, j);
//                 }
//             }
//         }

//         return count;
//     }

//     private void dfs(
//             char[][] grid,
//             boolean[][] vis,
//             int row,
//             int col) {

//         // Boundary check
//         if (row < 0 || row >= grid.length ||
//             col < 0 || col >= grid[0].length) {
//             return;
//         }

//         // Water
//         if (grid[row][col] == '0') {
//             return;
//         }

//         // Already visited
//         if (vis[row][col]) {
//             return;
//         }

//         // Mark visited
//         vis[row][col] = true;

//         // Up
//         dfs(grid, vis, row - 1, col);

//         // Down
//         dfs(grid, vis, row + 1, col);

//         // Left
//         dfs(grid, vis, row, col - 1);

//         // Right
//         dfs(grid, vis, row, col + 1);
//     }
// }
