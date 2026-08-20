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
// How would you solve this problem?

// Aap simple English me bol sakte ho:

// "Since the graph is a DAG, I can use DFS with backtracking. I will maintain a current path starting from node 0. Whenever I reach node n-1, I will add a copy of the current path to the result. For every neighbor, I will add it to the path, recursively explore it, and then remove it while backtracking. Since the graph has no cycles, I don't need a visited array."




// path.remove() kyu kar rahe hain?

// Ye sabse important interview point hai.

// Example:

// 0 → 1 → 3

// DFS ke time:

// path = [0]

// Then:

// path = [0,1]

// Then:

// path = [0,1,3]

// Target mil gaya.

// Answer mein add:

// [0,1,3]

// Ab DFS wapas aata hai.

// Hume next path try karna hai:

// 0 → 2 → 3

// Agar 1 ko remove nahi karenge:

// [0,1,2]

// galat path ban jayega.

// Isliye:

// path.remove(path.size() - 1);

// ke baad:

// [0]

// Phir 2 add:

// [0,2]

// and finally:

// [0,2,3]
// Simple definition:

// Backtracking means: choice lo → explore karo → choice undo karo → next choice try karo.



// Interview Explanation 🎤

// Interviewer ko English mein:

// "Since we need all possible paths from node 0 to node n-1, I use DFS with backtracking. I maintain a current path. At every node, I try all its outgoing neighbours. When I reach the target node, I add a copy of the current path to the result. After exploring a neighbour, I remove it from the current path so that I can explore the next possible path. Since the graph is a DAG, there are no cycles, so I don't need a visited array."

// Hindi mein:

// "Hume saare paths chahiye, isliye DFS use karenge. Ek current path maintain karenge. Har node ke saare neighbours ko explore karenge. Jab target n-1 mil jaaye to current path answer mein add kar denge. Uske baad last node ko remove karke next path try karenge. Isko backtracking kehte hain. Graph DAG hai, isliye cycle nahi hai aur visited array ki zarurat nahi hai."

// 7. visited[] kyu nahi hai?

// Ye interviewer zaroor pooch sakta hai.

// Normal graph DFS mein hum aksar:

// boolean[] visited

// use karte hain.

// Lekin yahan graph DAG (Directed Acyclic Graph) hai.

// DAG ka matlab:

// Directed graph with no cycle.

// Isliye DFS kisi node par cycle ke through wapas nahi aa sakta.

// Aur hume node ko permanently visited bhi nahi karna chahiye, kyunki same node different paths ka part ho sakta hai.

// Example:

//     1
//    / \
// 0      3
//    \ /
//     2

// Node 3 ko multiple paths se reach kiya ja sakta hai.

// Agar globally visited[3] = true kar diya, to ek valid path miss ho sakta hai.


// 10. Interview Follow-up Questions
// Q1. Why don't you use a visited array?

// Answer:

// Because the graph is guaranteed to be a DAG, so there are no cycles. Also, a node can belong to multiple valid paths, so globally marking it visited could incorrectly eliminate paths.

// Q2. Why do we use new ArrayList<>(path)?

// Ye bahut important hai.

// result.add(new ArrayList<>(path));

// Agar:

// result.add(path);

// likhenge, to result mein same path object ka reference repeatedly store hoga.

// Baad mein backtracking se path change hoga, aur stored answers bhi affected ho sakte hain.

// Isliye copy banate hain.

// Q3. What is backtracking?

// Choose → Explore → Undo → Try next choice.

// Code:

// path.add(neighbor);


// dfs(...);


// path.remove(path.size() - 1);
// Q4. Can we solve this using BFS?

// Yes, but DFS + backtracking is much more natural for all paths.

// BFS mein hume har state ke saath complete path carry karna padega, jisse memory usage badh sakti hai.

// Q5. Can we use DP?

// Yes, especially if the question changes from:

// "Return all paths"

// to:

// "Count the number of paths."

// Then DAG + DP/topological ordering can be useful.

// Q6. What if the graph is not a DAG?

// Then cycles possible hain.

// DFS mein cycle handling required hogi, for example recursion-stack/state tracking.

// ⭐ Is problem ka main pattern yaad rakho
// ALL PATHS
//    ↓
// DFS
//    ↓
// PATH LIST
//    ↓
// ADD NODE
//    ↓
// DFS
//    ↓
// REMOVE NODE
//    ↓
// BACKTRACK

// 11. visited[] kyu nahi use kiya?

// Interviewer ka common question.

// Normally graph DFS mein:

// boolean[] visited;

// use karte hain.

// But yahan graph DAG hai.

// DAG mein cycle nahi hoti.

// Also, ek node multiple valid paths ka part ho sakta hai.

// Example:

//        1
//       / \
//      0   3
//       \ /
//        2

// Node 3 ko:

// 0 → 1 → 3

// aur:

// 0 → 2 → 3

// dono paths se visit karna hai.

// Agar globally:

// visited[3] = true;

// kar diya, to second path miss ho sakta hai.

// Interview answer:

// "I don't use a global visited array because this problem asks for all paths, and the same node can appear in multiple valid paths. Also, the graph is guaranteed to be a DAG, so there are no cycles."

// 12. Why remove() is necessary?

// Interviewer:

// "Why are you removing the last element?"

// Answer:

// "After exploring one neighbour, I need to restore the previous path so that I can explore the next neighbour independently. This is backtracking."

// Example:

// 0 → 1 → 3

// Complete hone ke baad:

// [0,1,3]

// ko next path ke liye:

// [0]

// banana hai.

// Then:

// 0 → 2 → 3

// try karenge.

// 13. Why new ArrayList<>(path)?

// Interviewer:

// "Why can't you simply do result.add(path)?"

// Answer:

// "Because path is a mutable list and I modify it during backtracking. So I need to store a copy of the current path."

// Code:

// result.add(new ArrayList<>(path));
// 14. Complexity

// Suppose:

// P = number of possible paths
// L = average length of each path

// Then:

// Time = O(P × L)

// because we have to generate and store every path.

// Space:

// O(L)

// for current DFS path and recursion stack, excluding result.

// Including output:

// O(P × L)
// 15. Interview Explanation 🎤

// Agar interviewer bole:

// "Explain your approach."

// Tum ye bol sakte ho:

// "The graph is a DAG and I need to find all paths from node 0 to node n-1. I use DFS with backtracking. I maintain a current path. For every neighbour of the current node, I add that neighbour to the path and recursively explore it. When I reach node n-1, I add a copy of the current path to the result. After returning from DFS, I remove the last node to backtrack and try the next neighbour. Since the graph is a DAG, there are no cycles, so a visited array is not required."

// Ye interview ke liye strong explanation hai.

// 16. Interview Follow-up Questions
// Q1. Can you solve it using BFS?

// Answer: Yes. Queue mein complete paths store kar sakte hain, but DFS + backtracking is more natural and generally cleaner for enumerating all paths.

// Q2. What is backtracking?

// Answer:

// Choose a decision, explore it, undo the decision, and try another choice.

// Pattern:

// path.add(x);
// dfs(...);
// path.remove(path.size() - 1);
// Q3. Why is visited[] not required?

// Answer:

// Because the graph is guaranteed to be acyclic, and the same node can participate in multiple valid paths.

// Q4. What if the graph contains cycles?

// Then normal DFS mein infinite recursion ho sakti hai.

// Cycle detection ke liye:

// visited[]
// recursion stack

// ya node states use kar sakte hain.

// Q5. What if interviewer asks only "count the number of paths"?

// Then hume paths store karne ki zarurat nahi hogi.

// We can use DFS + DP/memoization.

// countPaths(node)

// ka meaning:

// Current node se target tak kitne paths hain?

// Q6. Why is this called Backtracking and not just DFS?

// DFS exploration karta hai, while yahan hum explicitly:

// add choice
// ↓
// explore
// ↓
// remove choice

// kar rahe hain.

// Ye DFS + backtracking pattern hai.
