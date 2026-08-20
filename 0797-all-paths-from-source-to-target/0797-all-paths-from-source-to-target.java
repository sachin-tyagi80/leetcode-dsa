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

// Interview me kaise explain karna hai?

// Agar interviewer bole:

// How would you solve this problem?

// Aap simple English me bol sakte ho:

// "Since the graph is a DAG, I can use DFS with backtracking. I will maintain a current path starting from node 0. Whenever I reach node n-1, I will add a copy of the current path to the result. For every neighbor, I will add it to the path, recursively explore it, and then remove it while backtracking. Since the graph has no cycles, I don't need a visited array."

// Ye interview ke liye strong explanation hai.

// 18. Interviewer Follow-up Questions
// Q1. Why DFS instead of BFS?

// Answer:

// DFS naturally explores one complete path at a time, so maintaining the current path is easy.

// BFS se bhi possible hai, but hume har queue element ke saath complete path maintain karna padega.

// Q2. Why do you use backtracking?

// Because after completing one path, we need to return to the previous node and explore another branch.

// Pattern:

// Add
// ↓
// DFS
// ↓
// Remove
// Q3. Why new ArrayList<>(path)?

// Because we need to store a snapshot/copy of the current path.

// result.add(new ArrayList<>(path));
// Q4. Why no visited array?

// Because input is guaranteed to be a DAG, so cycles cannot exist.

// Q5. Can BFS solve this problem?

// Yes.

// We can store:

// (node, path)

// in a queue.

// But DFS + backtracking is cleaner for this particular problem.

// Q6. What happens if the graph contains a cycle?

// Then the current solution without visited could recurse indefinitely.

// For a general directed graph, we'd need cycle handling.

// 19. One-line Pattern to Remember

// Is problem ka main pattern yaad rakho:

// path.add(node);


// dfs(...);


// path.remove(path.size() - 1);

// Backtracking = Choose → Explore → Undo

// Ye pattern future me bahut problems me kaam aayega, especially:

// Permutations
// Combinations
// Subsets
// Maze problems
// N-Queens
// Word Search
// All Paths problems
// ⭐ Interview takeaway

// All Paths from Source to Target = DFS + Backtracking

// Start at 0
//    ↓
// Choose neighbor
//    ↓
// DFS
//    ↓
// Reach n-1?
//    ↓
// Save path
//    ↓
// Backtrack
//    ↓
// Try next neighbor
