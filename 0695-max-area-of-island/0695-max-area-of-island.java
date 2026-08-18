class Solution {

    public int maxAreaOfIsland(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        int maxArea = 0;

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                // Land found
                if (grid[i][j] == 1) {

                    int area = dfs(grid, i, j);

                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;
    }

    private int dfs(int[][] grid, int row, int col) {

        // Out of boundary
        if (row < 0 || row >= grid.length ||
            col < 0 || col >= grid[0].length) {

            return 0;
        }

        // Water or already visited
        if (grid[row][col] == 0) {
            return 0;
        }

        // Mark visited curr cell
        grid[row][col] = 0;

        // Current cell count
        int area = 1;

        // Up
        area += dfs(grid, row - 1, col);

        // Down
        area += dfs(grid, row + 1, col);

        // Left
        area += dfs(grid, row, col - 1);

        // Right
        area += dfs(grid, row, col + 1);

        return area;
    }
}

// Interview Explanation

// Interviewer kahe:

// "Explain your approach."

// Tum bolo:

// English:
// "I will traverse the entire grid. Whenever I find an unvisited land cell, I start DFS from that cell. The DFS explores all four directions and calculates the total number of connected land cells, which is the area of that island. I mark every visited land cell as zero so that it is not processed again. Finally, I keep track of the maximum island area."

// Hindi:

// "Main poori grid traverse karunga. Jab mujhe koi unvisited land cell milega, main usse DFS start karunga. DFS four directions mein saare connected land cells ko visit karke island ka total area calculate karega. Visited cells ko zero mark kar dunga taaki dobara process na ho. Finally, main maximum area store karunga."

// Step 10: Complexity
// Time
// O(m × n)

// Why?

// Har cell maximum ek baar process hota hai.

// Space
// O(m × n)

// Worst case mein agar poori grid land hai, recursion stack bahut bada ho sakta hai.

// Step 11: Interview Follow-up Questions
// Q1. Can you solve it using BFS?

// Yes.

// DFS ki jagah:

// Queue<int[]> queue

// use karenge.

// Q2. Can you solve it without modifying the grid?

// Yes.

// boolean[][] visited

// use karenge.

// Instead of:

// grid[row][col] = 0;

// we use:

// visited[row][col] = true;
// Q3. Why only four directions?

// Because problem says 4-directionally connected.

// Diagonal:

// 1 0
// 0 1

// same island nahi hai.

// Q4. What if the grid contains only 0?

// Koi DFS start nahi hoga.

// maxArea = 0

// Answer:

// 0
// Q5. Why does DFS return int instead of boolean?

// Because humein island ka area chahiye.

// return area;

// Number of Islands (200) mein sirf existence/count chahiye tha, isliye void DFS bhi enough tha.

// Q6. What if diagonal connection is also allowed?

// Then 4 directions ke saath 4 diagonal directions bhi add karni hongi:

// ↖  ↑  ↗
// ←  1  →
// ↙  ↓  ↘
// Q7. Can Union-Find solve it?

// Yes. Each land cell is a node, and adjacent land cells are merged into the same component. The largest component size gives the answer.

// ⭐ Interview ke liye yaad rakhne wala pattern
// Find a 1
//    ↓
// DFS
//    ↓
// Mark visited
//    ↓
// Explore 4 directions
//    ↓
// Count area
//    ↓
// Return area
//    ↓
// Update maxArea
